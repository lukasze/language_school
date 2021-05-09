package com.codecool.language_school.data;

import com.codecool.language_school.model.attendance.Attendance;
import com.codecool.language_school.model.attendance.AttendanceEntry;
import com.codecool.language_school.model.attendance.AttendanceStatus;
import com.codecool.language_school.model.klass.Klass;
import com.codecool.language_school.model.user.*;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.Callable;

public class DataGenerator {

    private final static AttendanceStatus[] ATTENDANCE_STATUSES = AttendanceStatus.values();
    private final static List<String> firstNames = new ArrayList<>(Arrays.asList("June", "Kellie", "Kirstin", "Moira",
            "Veronica", "Ellen", "Evelyn", "Hilary", "Janine", "Lesley", "Abigail", "Diana", "Erica", "Roslyn",
            "Annmarie", "David", "John", "Paul", "Mark", "James", "Andrew", "Scott", "Steven", "Robert",
            "Stephen", "William", "Craig", "Michael", "Stuart", "Christopher", "Sabrina"));
    private final static List<String> lastNames = new ArrayList<>(Arrays.asList("Smith", "Johnson", "Williams", "Brown",
            "Jones", "Miller", "Davis", "Garcia", "Rodriguez", "Wilson", "Roberts", "Green", "Wood", "Clark",
            "Taylor", "Thomas", "Lewis", "Rose", "Davis", "Moore", "Quinn", "Murphy", "Graham", "Martin",
            "Stewart", "Anderson", "Reid", "Young", "Morrison", "Scott"));
    private final static int attendanceEntryCount = 100;
    private final static int attendanceCount = 25;
    private final static int studentCount = 25;
    private final static int teacherCount = 15;
    private final static int klassCount = 5;
    private final static int adminCount = 3;
    private final Set<String> takenLogins = new HashSet<>();
    private final List<AttendanceEntry> attendanceEntryList;
    private final List<Attendance> attendanceList;
    private final List<Klass> klassList;
    private final List<Student> studentList;
    private final List<Teacher> teacherList;
    private final List<Admin> adminList;
    private final EntityManager entityManager;
    private final Random random;

    public DataGenerator(EntityManager entityManager) {
        this.attendanceEntryList = new ArrayList<>();
        this.attendanceList = new ArrayList<>();
        this.klassList = new ArrayList<>();
        this.studentList = new ArrayList<>();
        this.teacherList = new ArrayList<>();
        this.adminList = new ArrayList<>();
        this.entityManager = entityManager;
        this.random = new Random();
    }

    public void populateDb() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        fillList(klassList, klassCount, this::createKlass);
        fillList(studentList, studentCount, this::createStudent);
        addStudentsToKlass();
        fillList(teacherList, teacherCount, this::createTeacher);
        addTeacherToKlass();
        fillList(adminList, adminCount, this::createAdmin);
        fillList(attendanceEntryList, attendanceEntryCount, this::createAttendanceEntry);
        fillList(attendanceList, attendanceCount, this::createAttendance);
        addAttendanceToAttendanceEntry();

        createTestData(entityManager);

        transaction.commit();
    }

    private void createTestData(EntityManager entityManager) {
        Student student = createTestStudent();
        Teacher teacher = createTestTeacher();
        Admin admin = createTestAdmin();
        entityManager.persist(student.getCredentials());
        entityManager.persist(teacher.getCredentials());
        entityManager.persist(admin.getCredentials());
        entityManager.persist(student);
        entityManager.persist(teacher);
        entityManager.persist(admin);
    }

    private Admin createTestAdmin() {
        Credentials credentials = new Credentials("ADMIN@MAIL.COM", "ADMIN", "ADMIN");
        return new Admin("BOLO", "KAPUSTA", 55, credentials);
    }

    private Teacher createTestTeacher() {
        Credentials credentials = new Credentials("BELFER@MAIL.COM", "MENTOR", "MENTOR");
        Teacher teacher = new Teacher("PATAJ", "SALAD", 35, credentials);
        teacher.addClass(klassList.get(1));
        teacher.addClass(klassList.get(2));
        return teacher;
    }

    private Student createTestStudent() {
        Credentials credentials = new Credentials("DUPA1@MAIL.COM", "DUPA1", "DUPA1");
        return new Student("TOLEK", "BANAN", 21, credentials, klassList.get(1));
    }

    private void addAttendanceToAttendanceEntry() {
        for (Attendance attendance : attendanceList) {
            for (AttendanceEntry attendanceEntry : attendance.getAttendanceHistory()) {
                attendanceEntry.setAttendance(attendance);
                entityManager.persist(attendanceEntry);
            }
        }
    }

    private void addTeacherToKlass() {
        for (Teacher teacher : teacherList) {
            for (Klass klass : teacher.getClassesAssigned()) {
                klass.setTeacher(teacher);
                entityManager.persist(klass);
            }
        }
    }

    private void addStudentsToKlass() {
        for (Student student : studentList) {
            Klass klass = student.getKlass();
            klass.addStudentToKlass(student);
            entityManager.persist(klass);
        }
    }

    private Attendance createAttendance() {
        int studentId = getRandomNumberInRange(0, studentList.size());
        Student student = studentList.get(studentId);
        Attendance attendance = new Attendance(student);
        int entriesCount = getRandomNumberInRange(3, 15);
        for (int i = 0; i < entriesCount; i++) {
            int entryId = getRandomNumberInRange(0, attendanceEntryList.size());
            AttendanceEntry attendanceEntry = attendanceEntryList.get(entryId);
            attendance.addAttendanceEntry(attendanceEntry);
        }
        return attendance;
    }

    private Admin createAdmin() {
        return (Admin) createUser(Role.ADMIN);
    }

    private Student createStudent() {
        return (Student) createUser(Role.STUDENT);
    }

    private Teacher createTeacher() {
        return (Teacher) createUser(Role.TEACHER);
    }

    private Klass createKlass() {
        int randomNumber = getRandomNumberInRange(1, 10);
        String randomLetter = RandomStringUtils.randomAlphabetic(1);
        String klassName = randomNumber + randomLetter.toUpperCase();
        return new Klass(klassName);
    }

    private User createUser(Role role) {
        String firstName = firstNames.get(getRandomNumberInRange(0, firstNames.size())).toUpperCase();
        String lastName = lastNames.get(getRandomNumberInRange(0, lastNames.size())).toUpperCase();
        int age = getRandomNumberInRange(18, 35);
        Credentials credentials = createCredentials(firstName, lastName);
        entityManager.persist(credentials);
        if (role.equals(Role.STUDENT)) {
            Klass klass = getRandomKlass();
            return new Student(firstName, lastName, age, credentials, klass);
        } else if (role.equals(Role.TEACHER)) {
            Teacher teacher = new Teacher(firstName, lastName, age, credentials);
            int klassAmount = getRandomNumberInRange(1, 6);
            for (int i = 0; i < klassAmount; i++) {
                teacher.addClass(getRandomKlass());
            }
            return teacher;
        } else {
            return new Admin(firstName, lastName, age, credentials);
        }
    }

    private Klass getRandomKlass() {
        return klassList.get(getRandomNumberInRange(0, klassList.size()));
    }

    private Credentials createCredentials(String firstName, String lastName) {
        String login = generateUniqueLogin(firstName, lastName);
        String email = (login + "@MAIL.COM");
        String password = RandomStringUtils.randomAlphanumeric(8, 20).toUpperCase();
        return new Credentials(email, login, password);
    }

    private String generateUniqueLogin(String firstName, String lastName) {
        boolean isValidLogin = false;
        String login = "";
        while (!isValidLogin) {
            String loginPart1 = getRandomNumberInRange(0, 10) > 7 ? firstName.substring(0, 1) : firstName;
            String separator = random.nextBoolean() ? "." : "_";
            String number = random.nextBoolean() ? "" : String.valueOf(getRandomNumberInRange(1, 1000));
            login = (random.nextBoolean() ? loginPart1 + separator + lastName : lastName + separator + loginPart1 + number)
                    .toUpperCase();
            if (!takenLogins.contains(login)) {
                isValidLogin = true;
            }
        }
        takenLogins.add(login);
        return login;
    }

    private AttendanceEntry createAttendanceEntry() {
        int randomYear = getRandomNumberInRange(2015, 2020);
        int randomMonth = getRandomNumberInRange(1, 12);
        int randomDay = getRandomNumberInRange(1, 30);
        AttendanceStatus randomAttendanceStatus = ATTENDANCE_STATUSES[getRandomNumberInRange(0, 4)];
        return new AttendanceEntry(LocalDate
                .of(randomYear, randomMonth, randomDay), randomAttendanceStatus);
    }
    
    private <T> void fillList(List<T> list, int count, Callable<T> method) {
        T object = null;
        for (int i = 0; i < count; i++) {
            try {
                object = method.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            list.add(object);
            entityManager.persist(object);
        }
    }

    private int getRandomNumberInRange(int origin, int bound) {
        return random.ints(origin, bound).findFirst().orElse(0);
    }
}

package com.codecool.language_school.model.user;

import com.codecool.language_school.model.attendance.Attendance;
import com.codecool.language_school.model.klass.Klass;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value= Role.Values.STUDENT_STRING)
public class Student extends User {

    @ManyToOne
    @NotNull
    @JoinTable(name="student_klass", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "klass_id", referencedColumnName = "id"))
    private Klass klass;

    @OneToOne(mappedBy = "student")
    private Attendance attendance;

    public Student(Klass klass) {
        this.klass = klass;
    }

    public Student(String name, String surname, int age, Credentials credentials, Klass klass) {
        super(name, surname, age, Role.STUDENT, credentials);
        this.klass = klass;
    }

    public Student() {}

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }
}

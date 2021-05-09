package com.codecool.language_school.model.klass;

import com.codecool.language_school.model.user.Student;
import com.codecool.language_school.model.user.Teacher;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Klass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    private String className;

    @OneToMany(mappedBy = "klass")
    private Set<Student> studentSet;

    @ManyToOne
    @JoinTable(name="teacher_klass", joinColumns = @JoinColumn(name = "klass_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"))
    private Teacher teacher;

    public Klass(String className) {
        this.className = className;
        studentSet = new HashSet<>();
    }

    public Klass() {
        studentSet = new HashSet<>();
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public long getId() {
        return id;
    }

    public void setId(long classId) {
        this.id = classId;
    }

    public void addStudentToKlass(Student student) {
        studentSet.add(student);
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setStudentSet(Set<Student> studentsSet) {
        this.studentSet = studentsSet;
    }

    public String getClassName() {
        return className;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }
}

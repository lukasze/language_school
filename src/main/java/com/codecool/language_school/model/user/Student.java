package com.codecool.language_school.model.user;

import com.codecool.language_school.model.attendance.Attendance;
import com.codecool.language_school.model.klass.Klass;
import com.sun.istack.NotNull;

import javax.persistence.*;

//@Annotation
public class Student extends User {

    //@Annotation
    private Klass klass;

    //@Annotation
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

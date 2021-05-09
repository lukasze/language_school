package com.codecool.language_school.model.user;

import com.codecool.language_school.model.klass.Klass;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue(value= Role.Values.TEACHER_STRING)
public class Teacher extends User {

    @OneToMany(mappedBy = "teacher")
    private final Set<Klass> classesAssigned;

    public Teacher() {
        classesAssigned = new HashSet<>();
    }

    public Teacher(String name, String surname, int age, Credentials credentials) {
        super(name, surname, age, Role.TEACHER, credentials);
        classesAssigned = new HashSet<>();
    }

    public Set<Klass> getClassesAssigned() {
        return classesAssigned;
    }

    public void addClass(Klass klass) {
        classesAssigned.add(klass);
    }
}

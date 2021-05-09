package com.codecool.language_school.model.user;

import com.sun.istack.NotNull;

import javax.persistence.*;

//@Annotation
public abstract class User {

    //@Annotation
    protected long id;

    //@Annotation
    protected String name;

    //@Annotation
    protected String surname;

    //@Annotation
    protected int age;

    //@Annotation
    protected Role role;

    //@Annotation
    protected Credentials credentials;

    public User(String name, String surname, int age, Role role, Credentials credentials) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.role = role;
        this.credentials = credentials;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public Role getRole() {
        return role;
    }

    public Credentials getCredentials() {
        return credentials;
    }
}

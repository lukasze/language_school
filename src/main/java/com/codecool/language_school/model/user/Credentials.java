package com.codecool.language_school.model.user;

import javax.persistence.*;

//@Annotation
public class Credentials {

    //@Annotation
    private long id;

    //@Annotation
    private String email;

    //@Annotation
    private String login;

    //@Annotation
    private String password;

    public Credentials(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public Credentials() {}

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}

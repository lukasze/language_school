package com.codecool.language_school.model.user;

import javax.persistence.*;

@Entity
public class Credentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
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

package com.codecool.language_school.model.user;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity(name = "AppUser")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ROLE_TYPE",
        discriminatorType=DiscriminatorType.STRING
)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    protected long id;

    @Basic
    protected String name;

    @Basic
    protected String surname;

    @Basic
    protected int age;

    @Enumerated(EnumType.STRING)
    protected Role role;

    @OneToOne
    @NotNull
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

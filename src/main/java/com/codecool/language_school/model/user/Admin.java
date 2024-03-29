package com.codecool.language_school.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value= Role.Values.ADMIN_STRING)
public class Admin extends User {

    public Admin() {
        super();
    }

    public Admin(String name, String surname, int age, Credentials credentials) {
        super(name, surname, age, Role.ADMIN, credentials);
    }
}

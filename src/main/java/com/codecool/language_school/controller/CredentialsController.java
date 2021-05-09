package com.codecool.language_school.controller;

import com.codecool.language_school.input.Input;
import com.codecool.language_school.model.user.Credentials;
import com.codecool.language_school.model.user.User;
import com.codecool.language_school.view.View;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CredentialsController extends Controller {

    public CredentialsController(EntityManager entityManager, Input input) {
        super(entityManager, input, new View());
    }

    public void register() {

    }

    public Optional<User> login() {
        String userLogin = input.getInput("Login: ");
        String userPassword = input.getInput("Password: ");
        Optional<Credentials> optionalCredentials = getOptional(Credentials.class, "Credentials", "login", userLogin);
        if (optionalCredentials.isEmpty()) {
            System.out.println("Invalid username.");
            return Optional.empty();
        }
        Credentials credentials = optionalCredentials.get();
        if (!credentials.getPassword().equals(userPassword)) {
            System.out.println("Invalid password.");
            return Optional.empty();
        }
        Optional<User> optionalUser = getOptional(User.class, "AppUser", "credentials", credentials);
        if (optionalUser.isEmpty()) {
            System.out.println("Login failed, user with given credentials could not be found.");
            return Optional.empty();
        }
        return optionalUser;
    }
}

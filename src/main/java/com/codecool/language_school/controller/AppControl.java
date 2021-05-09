package com.codecool.language_school.controller;

import com.codecool.language_school.input.Input;
import com.codecool.language_school.model.user.User;
import com.codecool.language_school.view.View;

import javax.persistence.EntityManager;
import java.util.Optional;


public class AppControl {

    private final EntityManager entityManager;
    private final CredentialsController credentialsController;
    private final View view;
    private final Input input;
    private Controller controller;
    private boolean isRunning;

    public AppControl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.view = new View();
        this.input = new Input();
        this.credentialsController = new CredentialsController(entityManager, input);
        this.isRunning = true;
    }

    public void run() {
        final int login = 1;
        final int register = 2;
        final int exit = 3;

        while (isRunning) {
            view.printCommands();
            int userChoice = input.getIntInput("", 3);

            switch (userChoice) {
                case (login):
                    handleLogin();
                    break;
                case (register):
                    credentialsController.register();
                    break;
                case (exit):
                    this.isRunning = false;
            }
        }
    }

    private void handleLogin() {
        Optional<User> optionalUser = credentialsController.login();
        optionalUser.ifPresent(user -> {
            assignControllerByUserRole(user);
            controller.setUser(user);
            controller.run();
        });
    }

    private void assignControllerByUserRole(User user) {
        switch (user.getRole()) {
            case ADMIN:
                controller = new AdminController(entityManager, input);
                break;
            case TEACHER:
                controller = new TeacherController(entityManager, input);
                break;
            case STUDENT:
                controller = new StudentController(entityManager, input);
        }
    }
}

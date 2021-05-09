package com.codecool.language_school.controller;

import com.codecool.language_school.input.Input;
import com.codecool.language_school.model.klass.Klass;
import com.codecool.language_school.model.user.*;
import com.codecool.language_school.view.View;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class Controller {

    protected final EntityManager entityManager;
    protected final Input input;
    protected final View view;
    protected Map<Integer, Runnable> commandsMap;
    protected boolean loggedIn;
    protected User user;

    public Controller(EntityManager entityManager, Input input, View view) {
        this.entityManager = entityManager;
        this.input = input;
        this.view = view;
        commandsMap = new HashMap<>();
        this.loggedIn = true;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void run() {
        EntityTransaction transaction = entityManager.getTransaction();
        while (loggedIn) {
            view.printCommands();
            int userChoice = input.getIntInput("", commandsMap.size());
            transaction.begin();
            commandsMap.get(userChoice).run();
            transaction.commit();
        }
    }

    public void logOut() {
        this.loggedIn = false;
    }

    protected void                                                                                                            endExecution(String message) {
        input.getEmptyInput(message);
        view.clearScreen();
    }

    protected <T, V> Optional<T> getOptional(Class<T> theClass, String tableName, String parameter, V value){
        String query = String.format("SELECT x from %s x WHERE x.%s = ?1", tableName, parameter);
        return entityManager.createQuery(query, theClass).setParameter(1, value)
                .getResultList().stream()
                .findFirst();
    }

    protected <T, V> List<T> getList(Class<T> theClass, String tableName, String parameter, V value){
        String query = String.format("SELECT x from %s x WHERE x.%s = ?1", tableName, parameter);
        return entityManager.createQuery(query, theClass).setParameter(1, value)
                .getResultList();
    }

    protected void deleteStudent() {
        deleteUser(Role.STUDENT);
    }

    protected void deleteMentor() {
        deleteUser(Role.TEACHER);
    }

    private void deleteUser(Role role) {
        long id = input.getIntInput("Id: ");
        Optional<User> optionalUser = entityManager.createQuery("SELECT u from AppUser u WHERE u.role = ?1 AND u.id = ?2", User.class)
                .setParameter(1, role).setParameter(2, id).getResultList().stream().findFirst();
        if (optionalUser.isPresent()){
            entityManager.remove(optionalUser.get());
            endExecution("Deleted "+ role.getName().toLowerCase() +". Press any key to continue");
        } else {
            endExecution("Could not find " + role.getName().toLowerCase() + " of given id. Press any key to continue");
        }
    }

    protected void createStudent() {
        createUser(Role.STUDENT);
    }

    protected void createMentor() {
        createUser(Role.TEACHER);
    }

    private void createUser(Role role) {
        User user = null;
        String name = input.getInput("Name: ");
        String surname = input.getInput("Surname: ");
        int age = input.getIntInput("Age: ", 100);
        Credentials credentials = createCredentials();
        entityManager.persist(credentials);
        switch (role) {
            case STUDENT:
                Klass klass = getExisting("Klass", Klass.class);
                user = new Student(name, surname, age, credentials, klass);
                break;
            case TEACHER:
                user = new Teacher(name, surname, age, credentials);
                int klassAmount = input.getIntInput("Number of classes the teacher has: ", 10);
                for (int i = 0; i < klassAmount; i++) {
                    ((Teacher) user).addClass(getExisting("Klass", Klass.class));
                }
                break;
            case ADMIN:
                user = new Admin(name, surname, age, credentials);
        }
        entityManager.persist(user);
        endExecution("New " + role.getName().toLowerCase() +" created. Press any key to continue");
    }

    protected <T> T getExisting(String dataType, Class<T> theClass) {
        boolean exists = false;
        T data = null;
        String query = "SELECT x FROM " + dataType + " x WHERE x.id = ?1";
        while (!exists) {
            long id = input.getIntInput(dataType + " id: ");
            Optional<T> optionalT = entityManager
                    .createQuery(query, theClass)
                    .setParameter(1, id).getResultList().stream().findFirst();
            if (optionalT.isEmpty()) {
                input.getEmptyInput(dataType + " with given id does not exist." +
                        "Provide different id.");
            } else {
                exists = true;
                data = optionalT.get();
            }
        }
        return data;
    }

    protected Credentials createCredentials() {
        String login = createUnique("Login");
        String email = createUnique("Email");
        String password = input.getInput("Password: ");
        return new Credentials(email, login, password);
    }

    private String createUnique(String credentialType) {
        boolean validCredentials = false;
        String userCredential = "";
        String query = "SELECT c FROM Credentials c WHERE c." + credentialType.toLowerCase() + " = ?1";
        while (!validCredentials) {
            userCredential = input.getInput(credentialType + ": ");
            Optional<Credentials> optionalCredential = entityManager
                    .createQuery(query, Credentials.class)
                    .setParameter(1, userCredential).getResultList().stream().findFirst();
            if (optionalCredential.isPresent()) {
                input.getEmptyInput("User with given " + credentialType + " already exists." +
                        "Provide different credentials.");
            } else {
                validCredentials = true;
            }
        }
        return userCredential;
    }
}

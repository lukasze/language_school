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

    /**
     * Loop in which program stays while user is logged in.
     * It wraps the specific Controller class method calls
     * with logic committing transaction to database.
     *
     * Gets EntityTransaction from EntityManager.
     * Loop starts.
     * Prints commands calling View.
     * Gets input from user, with bound of commandsMap.size().
     * Starts transaction.
     * Calls commandsMap.get(userChoice).run().
     * Commits transaction.
     *
     */
    public void run() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void logOut() {
        this.loggedIn = false;
    }

    protected void endExecution(String message) {
        input.getEmptyInput(message);
        view.clearScreen();
    }

    /**
     * Generic method retrieving Optional of single object from database by single parameter.
     * Method uses String.format() and Query.setParameter() to create dynamic queries
     *
     * @param theClass class to receive from query
     * @param tableName database table name
     * @param parameter database column name to look by
     * @param value database column value to look by
     * @param <T> type to receive from query
     * @param <V> type of parameter passed to query
     * @return Optional of <T> by Stream findFirst() on result list
     */
    protected <T, V> Optional<T> getOptional(Class<T> theClass, String tableName, String parameter, V value){
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Generic method retrieving list of objects from database by single parameter.
     * Method uses String.format() and Query.setParameter() to create dynamic queries
     *
     * @param theClass class to receive from query
     * @param tableName database table name
     * @param parameter database column name to look by
     * @param value database column value to look by
     * @param <T> type to receive from query
     * @param <V> type of parameter passed to query
     * @return Optional of <T> by Stream findFirst() on result list
     */
    protected <T, V> List<T> getList(Class<T> theClass, String tableName, String parameter, V value){
        throw new UnsupportedOperationException("Not yet implemented");
    }

    protected void deleteStudent() {
        deleteUser(Role.STUDENT);
    }

    protected void deleteMentor() {
        deleteUser(Role.TEACHER);
    }

    /**
     * Method removing User of given role.
     * It finds User by doubly parametrized query (Role and id)
     * If User is found, removes it,
     * otherwise inform that User of given Role and given id could not be found
     *
     * @param role user Role to remove by
     */
    private void deleteUser(Role role) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    protected void createStudent() {
        createUser(Role.STUDENT);
    }

    protected void createMentor() {
        createUser(Role.TEACHER);
    }

    /**
     * Method creating user of given role and persisting the user and their credentials.
     *
     * Gets name, surname and age by calling Input methods.
     * Gets credentials by Controller method.
     * Persists credentials - if credentials which user relates to are not in database
     * when user is to be persisted, an error will occur.
     * Uses switch case on Role from parameter.
     * For Role.Student gets Klass using Controller.getExisting() and creates Student.
     * For Role.Teacher created Teacher and asks for amount of classes he has,
     * in loop assigns the classes using Controller.getExisting().
     * For Role.Admin creates Admin.
     * Persists user.
     * Ends execution with appropriate message.
     *
     * @param role User Role indicating type of user to create
     */
    private void createUser(Role role) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Generic method retrieving object from database by id.
     * Method uses String.format() and Query.setParameter() to create dynamic queries.
     * Method keeps asking for id until Optional of <T> is present.
     *
     * @param dataType table name
     * @param theClass class to receive from query
     * @param <T> type to receive from query
     * @return Object of type <T> by Stream findFirst() on result list
     */
    protected <T> T getExisting(String dataType, Class<T> theClass) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    protected Credentials createCredentials() {
        String login = createUnique("Login");
        String email = createUnique("Email");
        String password = input.getInput("Password: ");
        return new Credentials(email, login, password);
    }

    /**
     * Method generating unique Credentials email or login.
     * Method uses String.format() and Query.setParameter() to create dynamic queries.
     * Gets input from user and looks for Credentials in database with that value
     * While Optional of Credentials is present, keeps asking for a value that will be unique
     *
     * @param credentialType login or email
     * @return unique login or email - Credential component of type String
     */
    private String createUnique(String credentialType) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

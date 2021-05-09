package com.codecool.language_school.controller;

import com.codecool.language_school.input.Input;
import com.codecool.language_school.model.user.Role;
import com.codecool.language_school.model.user.User;
import com.codecool.language_school.view.AdminView;

import javax.persistence.EntityManager;
import java.util.List;

public class AdminController extends Controller {

    public AdminController(EntityManager entityManager, Input input) {
        super(entityManager, input, new AdminView());
        commandsMap.put(1, this::enlistStudents);
        commandsMap.put(2, this::enlistMentors);
        commandsMap.put(3, this::createStudent);
        commandsMap.put(4, this::createMentor);
        commandsMap.put(5, this::deleteStudent);
        commandsMap.put(6, this::deleteMentor);
        commandsMap.put(7, this::editStudent);
        commandsMap.put(8, this::editMentor);
        commandsMap.put(0, this::logOut);
    }

    private void enlistStudents() {
        List<User> students = getList(User.class, "AppUser", "role", Role.STUDENT);
        view.printListOfUsers(students);
        endExecution("Press any key to continue");
    }

    private void enlistMentors() {
        List<User> teachers = getList(User.class, "AppUser", "role", Role.TEACHER);
        view.printListOfUsers(teachers);
        endExecution("Press any key to continue");
    }

    private void editStudent() {

        throw new UnsupportedOperationException("Not yet implemented");

    }

    private void editMentor() {

        throw new UnsupportedOperationException("Not yet implemented");

    }
}



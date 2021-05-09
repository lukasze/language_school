package com.codecool.language_school.controller;

import com.codecool.language_school.input.Input;
import com.codecool.language_school.view.TeacherView;

import javax.persistence.EntityManager;

public class TeacherController extends Controller {

    public TeacherController(EntityManager entityManager, Input input) {
        super(entityManager, input, new TeacherView());
        commandsMap.put(1, this::enlistStudents);
        commandsMap.put(2, this::createStudent);
        commandsMap.put(3, this::updateStudent);
        commandsMap.put(4, this::checkStudentAttendance);
        commandsMap.put(5, this::editData);
        commandsMap.put(6, this::assignStudentToClass);
        commandsMap.put(7, this::checkStudentsInClass);
        commandsMap.put(0, this::logOut);
    }

    private void enlistStudents() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void updateStudent() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void checkStudentAttendance() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void editData() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void assignStudentToClass() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void checkStudentsInClass() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

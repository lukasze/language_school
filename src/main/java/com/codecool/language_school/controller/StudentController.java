package com.codecool.language_school.controller;

import com.codecool.language_school.input.Input;
import com.codecool.language_school.model.attendance.Attendance;
import com.codecool.language_school.model.attendance.AttendanceEntry;
import com.codecool.language_school.view.StudentView;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

public class StudentController extends Controller {

    public StudentController(EntityManager entityManager, Input input) {
        super(entityManager, input, new StudentView());
        commandsMap = new HashMap<>();
        commandsMap.put(1, this::checkAttendanceForDate);
        commandsMap.put(2, this::editData);
        commandsMap.put(3, this::assignToClass);
        commandsMap.put(4, this::checkClass);
        commandsMap.put(5, this::quitClass);
        commandsMap.put(0, this::logOut);
    }

    // use this as reference solution
    private void checkAttendanceForDate() {
        Optional<Attendance> optionalAttendance = getOptional(Attendance.class, "Attendance", "student", user);
        if (optionalAttendance.isEmpty()) {
            input.getEmptyInput("Could not find any attendance records for student. Press any key to continue.");
            return;
        }
        LocalDate date = input.getDateInput();
        Attendance attendance = optionalAttendance.get();
        Optional<AttendanceEntry> attendanceEntry = attendance.getAttendanceHistory().stream()
                                                              .filter(entry -> date.equals(entry.getDate()))
                                                              .findFirst();
        attendanceEntry.ifPresentOrElse(view::printAttendanceEntry, () -> input
                .getEmptyInput("Could not find attendance entry records for given date. Press any key to continue."));
    }

    private void editData() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void assignToClass() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void checkClass() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void quitClass() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}


package com.codecool.language_school.view;

import com.codecool.language_school.model.attendance.AttendanceEntry;
import com.codecool.language_school.model.user.User;

import java.util.List;

public class View {
    protected String[] commandList;

    public View() {
        this.commandList = new String[]{"1. Login", "2. Register", "3. Exit"};
    }

    public void printCommands() {
        for (String command : commandList) {
            System.out.println(command);
        }
    }

    public void clearScreen() {
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printAttendanceEntry(AttendanceEntry attendanceEntry) {
        System.out.println(toFormattedString(attendanceEntry));
    }

    public void printListOfUsers(List<User> userList) {
        System.out.println("\n");
        for (User user : userList) {
            System.out.println(toFormattedString(user));
        }
    }

    private String toFormattedString(AttendanceEntry attendanceEntry) {
        String idFormat = fixedLengthString(String.valueOf(attendanceEntry.getId()), 6);
        String dateFormat = fixedLengthString(String.valueOf(attendanceEntry.getDate()), 12);
        String statusFormat = fixedLengthString(String.valueOf(attendanceEntry.getStatus()), 12);
        return "" + idFormat +
                ". " + dateFormat +
                " " + statusFormat;
    }

    private String toFormattedString(User user) {
        String idFormat = fixedLengthString(String.valueOf(user.getId()), 6);
        String firstNameFormat = fixedLengthString(user.getName(), 15);
        String lastNameFormat = fixedLengthString(user.getSurname(), 15);
        String roleFormat = fixedLengthString(String.valueOf(user.getRole()), 45);
        String ageFormat = fixedLengthString(String.valueOf(user.getAge()), 15);
        return "" + idFormat +
                ". " + firstNameFormat +
                " " + lastNameFormat +
                " " + ageFormat +
                " " + roleFormat;
    }


    private String fixedLengthString(String string, int length) {
        return String.format("%1$" + length + "s", string);
    }

}

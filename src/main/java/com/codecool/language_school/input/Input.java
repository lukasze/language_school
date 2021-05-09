package com.codecool.language_school.input;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Input {
    private final Scanner scan;

    public Input() {
        scan = new Scanner(System.in);
        scan.useDelimiter(System.lineSeparator());
    }

    public Scanner getScan() {
        return scan;
    }

    public String getInput(String title) {
        System.out.println(title);
        boolean validInput = false;
        String userInput = "";
        while (!validInput) {
            userInput = scan.next().toUpperCase();
            if (!userInput.equals("")) {
                validInput = true;
            } else {
                System.out.println("Your input must contain at least one character. Enter again: ");
            }
        }
        return userInput;
    }

    public void getEmptyInput(String title) {
        System.out.println(title);
        scan.next();
    }

    public int getIntInput(String title) {
        System.out.println(title);
        String userInput;
        int userInt = 1;
        boolean validInput = false;
        while (!validInput) {
            userInput = scan.next();
            if (!userInput.equals("") && userInput.matches("^[0-9]*$")) {
                validInput = true;
                userInt = Integer.parseInt(userInput);
            } else {
                System.out.println("Invalid input. Enter again: ");
            }
        }
        return userInt;
    }


    public int getIntInput(String title, int range) {
        System.out.println(title);
        String userInput;
        int userInt = 1;
        boolean validInput = false;
        while (!validInput) {
            userInput = scan.next();
            if (!userInput.equals("") && userInput.matches("^[0-9]*$") && userInt <= range) {
                userInt = Integer.parseInt(userInput);
                validInput = true;
            }
        }
        return userInt;
    }

    public LocalDate getDateInput() {
        boolean isValidDate = false;
        String stringDate;
        LocalDate date = LocalDate.now();
        while (!isValidDate) {
            stringDate = getInput("Provide date in format YYYY-MM-DD");
            try {
                date = LocalDate.parse(stringDate);
                isValidDate = true;
            } catch (DateTimeParseException e) {
                getEmptyInput("Invalid date format. Press any key to continue");
            }
        }
        return date;
    }
}

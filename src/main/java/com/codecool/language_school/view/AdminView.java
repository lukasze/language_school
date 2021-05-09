package com.codecool.language_school.view;

public class AdminView extends View {

    public AdminView() {
        commandList = new String[]{"1. Enlist students", "2. Enlist mentors",
                "3. Create student", "4. Create mentor",
                "5. Delete student", "6. Delete mentor",
                "7. Edit student", "8. Edit mentor", "0. Logout"};
    }
}

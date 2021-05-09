package com.codecool.language_school.model.user;

public enum Role {
    ADMIN(Values.ADMIN_STRING), TEACHER(Values.TEACHER_STRING), STUDENT(Values.STUDENT_STRING);

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static class Values {
        public static final String ADMIN_STRING = "ADMIN";
        public static final String TEACHER_STRING = "TEACHER";
        public static final String STUDENT_STRING = "STUDENT";
    }
}

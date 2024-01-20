package com.management.taskifypro.model.enums;

public enum ERole {
    MEMBER("MEMBER"),
    ADMIN("ADMIN");

    private final String name;

    ERole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

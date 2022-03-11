package com.epam.library.entity;


public enum Role {
    ADMIN("admin"),
    LIBRARIAN("librarian"),
    READER("reader");

    private final String roleName;

    Role(String role) {
        this.roleName = role;
    }

    public String getRoleName() {
        return roleName;
    }
}

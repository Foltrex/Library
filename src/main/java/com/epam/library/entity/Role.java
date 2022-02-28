package com.epam.library.entity;


import java.io.Serializable;

public enum Role implements Serializable {
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

package com.epam.library.entity;


import com.epam.library.command.CommandName;

import java.util.HashMap;
import java.util.Map;

public enum Role {
    ADMIN("admin"),
    LIBRARIAN("librarian"),
    READER("reader");

    private final String roleName;

    private static final String SPACE = " ";
    private static final String DOT = ".";

    private static final Map<String, Role> ELEMENTS = new HashMap<>();
    static {
        for (Role role: Role.values()) {
            ELEMENTS.put(role.roleName, role);
        }
    }

    Role(String role) {
        this.roleName = role;
    }

    public static Role valueOfRoleName(String roleName) {
        return ELEMENTS.get(roleName);
    }

    public String getRoleName() {
        return roleName;
    }

    public String getLocalizedStatusName() {
        String roleFormattedString = roleName.toLowerCase().replaceAll(SPACE, DOT);
        return String.format("role.%s", roleFormattedString);
    }
}

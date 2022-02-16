package com.epam.library.logic.security;

import com.epam.library.entity.Role;
import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SecurityConfig {
    private static SecurityConfig instance;
    private static final Lock LOCK = new ReentrantLock();

    // TODO: add allowed sites for every role
    private final Map<Role, List<String>> allowedPages = ImmutableMap.of(
            Role.ADMIN,     Arrays.asList("index.jsp"),
            Role.LIBRARIAN, Arrays.asList("index.jsp"),
            Role.READER,    Arrays.asList("index.jsp")
    );

    private SecurityConfig() {
    }

    public static SecurityConfig getInstance() {
        SecurityConfig localInstance = instance;
        if (localInstance == null) {
            LOCK.lock();
            localInstance = instance;
            try {
                if (localInstance == null) {
                    localInstance = new SecurityConfig();
                    instance = localInstance;
                }
            } finally {
                LOCK.unlock();
            }
        }

        return localInstance;
    }

    public List<String> getAllowedPagesForRole(Role role) {
        return allowedPages.get(role);
    }
}

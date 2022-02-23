package com.epam.library.security;

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

    // pages
    private final String LOGIN = "index.jsp";
    private final String CATALOG = "catalog.jsp";
    private final String CHANGING_BOOK = "changing-book.jsp";
    private final String READERS = "readers.jsp";
    private final String LIBRARIANS = "librarians.jsp";
    private final String BOOK = "book.jsp";
    private final String LOANS = "loans.jsp";


    private final Map<Role, List<String>> allowedPages = ImmutableMap.of(
            Role.ADMIN,     Arrays.asList(LOGIN, CATALOG, CHANGING_BOOK, READERS, LIBRARIANS, BOOK),
            Role.LIBRARIAN, Arrays.asList(LOGIN, CATALOG, CHANGING_BOOK, BOOK, LOANS),
            Role.READER,    Arrays.asList(LOGIN, CATALOG, BOOK, LOANS)
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

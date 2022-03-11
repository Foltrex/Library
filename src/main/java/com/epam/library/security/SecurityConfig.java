package com.epam.library.security;

import com.epam.library.command.CommandName;
import com.epam.library.entity.Role;
import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SecurityConfig {
    private static SecurityConfig instance;
    private static final Lock LOCK = new ReentrantLock();

    // pages
    private static final String LOGIN = "index.jsp";
    private static final String CATALOG = "books.jsp";
    private static final String CHANGING_BOOK = "changing-book.jsp";
    private static final String READERS = "readers.jsp";
    private static final String LIBRARIANS = "librarians.jsp";
    private static final String BOOK = "book.jsp";
    private static final String LOANS = "loans.jsp";


    private final Map<Role, List<String>> allowedPages = new LinkedHashMap<Role, List<String>>() {{
        put(Role.ADMIN, Arrays.asList(LOGIN, CATALOG, CHANGING_BOOK, READERS, LIBRARIANS, BOOK));
        put(Role.LIBRARIAN, Arrays.asList(LOGIN, CATALOG, CHANGING_BOOK, BOOK, LOANS));
        put(Role.READER, Arrays.asList(LOGIN, CATALOG, BOOK, LOANS));
    }};

    private final Map<Role, List<CommandName>> allowedCommands = new LinkedHashMap<Role, List<CommandName>>() {{
        put(Role.ADMIN, Arrays.asList(CommandName.LOGIN, CommandName.SHOW_BOOKS));
        put(Role.LIBRARIAN, Arrays.asList(CommandName.LOGIN, CommandName.SHOW_BORROWS));
        put(Role.READER, Arrays.asList(CommandName.LOGIN, CommandName.SHOW_BORROWS));
    }};

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

    public List<CommandName> getAllowedCommandsForRole(Role role) {
        return allowedCommands.get(role);
    }
}

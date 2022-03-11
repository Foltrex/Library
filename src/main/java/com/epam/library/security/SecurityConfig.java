package com.epam.library.security;

import com.epam.library.command.CommandName;
import com.epam.library.entity.Role;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SecurityConfig {
    private static SecurityConfig instance;
    private static final Lock LOCK = new ReentrantLock();

    private final Map<Role, List<CommandName>> allowedCommands;

    private SecurityConfig() {
        allowedCommands = createAllowedCommands();
    }

    private Map<Role, List<CommandName>> createAllowedCommands() {
        List<CommandName> adminCommands = Arrays.asList(
                CommandName.LOGIN, CommandName.LOGOUT, CommandName.CHANGE_LANGUAGE,
                CommandName.SHOW_BORROWS, CommandName.SHOW_BOOKS, CommandName.SEARCH_BOOK,
                CommandName.SHOW_BOOK_DETAILS, CommandName.SAVE_BOOK, CommandName.ADD_BOOK,
                CommandName.CHANGE_USER_BLOCKING, CommandName.SHOW_READERS, CommandName.SHOW_LIBRARIANS,
                CommandName.SHOW_AUTHORS, CommandName.SHOW_AUTHOR_BOOKS, CommandName.SHOW_GENRES,
                CommandName.ADD_GENRE, CommandName.SHOW_GENRE_BOOKS
        );

        List<CommandName> librarianCommands = Arrays.asList(
                CommandName.LOGIN, CommandName.LOGOUT, CommandName.CHANGE_LANGUAGE,
                CommandName.SHOW_BORROWS, CommandName.CHANGE_BORROW, CommandName.DELETE_BORROW,
                CommandName.SAVE_BORROW, CommandName.SHOW_BOOKS, CommandName.SEARCH_BOOK,
                CommandName.SHOW_READERS, CommandName.SHOW_AUTHORS, CommandName.SHOW_AUTHOR_BOOKS,
                CommandName.SHOW_GENRES, CommandName.SHOW_GENRE_BOOKS

        );

        List<CommandName> readerCommand = Arrays.asList(
                CommandName.LOGIN, CommandName.LOGOUT, CommandName.CHANGE_LANGUAGE,
                CommandName.SHOW_BOOKS, CommandName.BORROW_BOOK, CommandName.SEARCH_BOOK,
                CommandName.SHOW_AUTHORS, CommandName.SHOW_AUTHOR_BOOKS, CommandName.SHOW_GENRES,
                CommandName.SHOW_GENRE_BOOKS
        );

        return new LinkedHashMap<>() {{
            put(Role.ADMIN, adminCommands);
            put(Role.LIBRARIAN, librarianCommands);
            put(Role.READER, readerCommand);
        }};
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

    public List<CommandName> getAllowedCommandsForRole(Role role) {
        return allowedCommands.get(role);
    }
}

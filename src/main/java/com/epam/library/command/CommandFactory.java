package com.epam.library.command.factory;

import com.epam.library.command.*;

public class CommandFactory {

    public Command defineCommand(String command) {
        switch (command) {
            case "login":
                return new LoginCommand();
            case "logout":
                return new LogoutCommand();
            case "book_details":
                return new BookDetailsCommand();
            case "search":
                return new SearchCommand();
            case "select_catalog":
                return new SelectGenreCommand();
            default:
                throw new IllegalArgumentException("Unknown command = " + command);
        }
    }
}

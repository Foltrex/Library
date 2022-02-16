package com.epam.library.command;

import com.epam.library.dao.DaoHelperFactory;
import com.epam.library.service.UserServiceImpl;

public class CommandFactory {

    public Command defineCommand(String command) {
        switch (command) {
            case "login":
                return new LoginCommand(new UserServiceImpl(new DaoHelperFactory()));
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

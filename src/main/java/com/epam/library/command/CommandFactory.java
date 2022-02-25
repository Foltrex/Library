package com.epam.library.command;

import com.epam.library.dao.DaoHelperFactory;
import com.epam.library.entity.Role;
import com.epam.library.service.implementation.AdminServiceImpl;
import com.epam.library.service.implementation.UserServiceImpl;

public class CommandFactory {

    public Command defineCommand(String command) {
        switch (command) {
            case "login":
                return new LoginCommand(new UserServiceImpl(new DaoHelperFactory()));
            case "logout":
                return new LogoutCommand();
            case "book_details":
                return new BookDetailsCommand();
            case "show_readers":
                return new ShowUsersCommand(new AdminServiceImpl(new DaoHelperFactory()), Role.READER);
            case "show_librarians":
                return new ShowUsersCommand(new AdminServiceImpl(new DaoHelperFactory()), Role.LIBRARIAN);
            default:
                throw new IllegalArgumentException("Unknown command = " + command);
        }
    }
}

package com.epam.library.command;

import com.epam.library.command.implementation.*;
import com.epam.library.dao.DaoHelperFactory;
import com.epam.library.entity.Role;
import com.epam.library.service.implementation.*;

public class CommandFactory {

    private final DaoHelperFactory factory = new DaoHelperFactory();

    public Command defineCommand(String command) {
        switch (command) {
            case "login":
                return new LoginCommand(new UserServiceImpl(factory));
            case "logout":
                return new LogoutCommand();
            case "change_language":
                return new ChangeLocaleCommand();
            case "show_rentals":
                return new ShowBookRentalsListCommand(new BookRentalServiceImpl(factory));
            case "change_rental":
                return new ChangeRentalCommand(new BookRentalServiceImpl(factory));
            case "delete_rental":
                return new DeleteRentalCommand(new BookRentalServiceImpl(factory));
            case "save_rental":
                return new SaveBookRentalToDatabaseCommand(new BookRentalServiceImpl(factory));
            case "show_book_details":
                return new ShowBookDetailsCommand
                        (new BookServiceImpl(factory), new AuthorServiceImpl(factory), new GenreServiceImpl(factory));
            case "show_books":
                return new ShowBooksCommand(new BookServiceImpl(factory));
            case "save_book_to_database":
                return new SaveBookToDatabaseCommand(new BookServiceImpl(factory));
            case "show_readers":
                return new ShowUsersCommand(new AdminServiceImpl(factory), Role.READER);
            case "show_librarians":
                return new ShowUsersCommand(new AdminServiceImpl(factory), Role.LIBRARIAN);
            default:
                throw new IllegalArgumentException("Unknown command = " + command);
        }
    }
}

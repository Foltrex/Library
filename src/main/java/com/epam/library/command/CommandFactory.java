package com.epam.library.command;

import com.epam.library.command.impl.*;
import com.epam.library.dao.DaoHelperFactory;
import com.epam.library.entity.Role;
import com.epam.library.service.impl.*;
import com.epam.library.validator.impl.InputDateValidatorImpl;
import com.epam.library.validator.impl.InputStockValidatorImpl;

/** Creates command object */
public class CommandFactory {

    private final DaoHelperFactory factory = new DaoHelperFactory();

    /** Defines current command */
    public Command defineCommand(String command) {
        CommandName commandName = CommandName.valueOfName(command);

        return switch (commandName) {
            case LOGIN -> new LoginCommand(new UserServiceImpl(factory), new ReCaptchaChecker());
            case LOGOUT -> new LogoutCommand();
            case SAVE_USER -> new SaveUserCommand(new UserServiceImpl(factory));
            case SHOW_RENTALS -> new ShowBookRentalsCommand(new BookRentalServiceImpl(factory));
            case SHOW_BOOK_RENTAL_DETAILS -> new ShowBookRentalDetailsCommand(new BookRentalServiceImpl(factory));
            case DELETE_RENTAL -> new DeleteBookRentalCommand(new BookRentalServiceImpl(factory));
            case SAVE_RENTAL -> new SaveBookRentalCommand(new BookRentalServiceImpl(factory), new InputDateValidatorImpl());
            case SHOW_BOOK_DETAILS -> new ShowBookDetailsCommand
                    (new BookServiceImpl(factory), new AuthorServiceImpl(factory), new GenreServiceImpl(factory));
            case SHOW_BOOKS -> new ShowBooksCommand(new BookServiceImpl(factory));
            case BORROW_BOOK -> new BorrowBookCommand(new BookRentalServiceImpl(factory));
            case SEARCH_BOOK -> new SearchBookCommand(new BookServiceImpl(factory));
            case SAVE_BOOK -> new SaveBookCommand(new BookServiceImpl(factory), new InputStockValidatorImpl());
            case ADD_BOOK -> new AddBookCommand(new AuthorServiceImpl(factory), new GenreServiceImpl(factory));
            case CHANGE_USER_BLOCKING -> new ChangeUserBlockingCommand(new AdminServiceImpl(factory));
            case SHOW_READERS -> new ShowUsersCommand(new AdminServiceImpl(factory), Role.READER);
            case SHOW_LIBRARIANS -> new ShowUsersCommand(new AdminServiceImpl(factory), Role.LIBRARIAN);
            case SHOW_AUTHORS -> new ShowAuthorsCommand(new AuthorServiceImpl(factory));
            case ADD_AUTHOR -> new AddAuthorCommand(new AuthorServiceImpl(factory));
            case SHOW_AUTHOR_BOOKS -> new ShowAuthorBooksCommand(new BookServiceImpl(factory));
            case SHOW_GENRES -> new ShowGenresCommand(new GenreServiceImpl(factory));
            case ADD_GENRE -> new AddGenreCommand(new GenreServiceImpl(factory));
            case SHOW_GENRE_BOOKS -> new ShowGenreBooksCommand(new BookServiceImpl(factory));
            case SHOW_ABOUT_PAGE -> new ShowPageCommand(Page.ABOUT.getPath());
        };
    }
}

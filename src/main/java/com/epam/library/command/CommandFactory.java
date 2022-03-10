package com.epam.library.command;

import com.epam.library.command.implementation.*;
import com.epam.library.dao.DaoHelperFactory;
import com.epam.library.entity.Role;
import com.epam.library.service.implementation.*;

public class CommandFactory {

    private final DaoHelperFactory factory = new DaoHelperFactory();


    public Command defineCommand(String command) {
        CommandName commandName = CommandName.valueOfName(command);

        switch (commandName) {
            case LOGIN:
                return new LoginCommand(new UserServiceImpl(factory));
            case LOGOUT:
                return new LogoutCommand();
            case CHANGE_LANGUAGE:
                return new ChangeLocaleCommand();
            case SHOW_BORROWS:
                return new ShowBookBorrowsListCommand(new BookBorrowServiceImpl(factory));
            case CHANGE_BORROW:
                return new ChangeBorrowCommand(new BookBorrowServiceImpl(factory));
            case DELETE_BORROW:
                return new DeleteBorrowCommand(new BookBorrowServiceImpl(factory));
            case SAVE_BORROW:
                return new SaveBookBorrowToDatabaseCommand(new BookBorrowServiceImpl(factory));
            case SHOW_BOOK_DETAILS:
                return new ShowBookDetailsCommand
                        (new BookServiceImpl(factory), new AuthorServiceImpl(factory), new GenreServiceImpl(factory));
            case SHOW_BOOKS:
                return new ShowBooksCommand(new BookServiceImpl(factory));
            case BORROW_BOOK:
                return new BorrowBookCommand(new BookBorrowServiceImpl(factory));
            case SEARCH_BOOK:
                return new SearchBookCommand(new BookServiceImpl(factory));
            case SAVE_BOOK:
                return new SaveBookToDatabaseCommand(new BookServiceImpl(factory));
            case ADD_BOOK:
                return new AddBookCommand(new AuthorServiceImpl(factory), new GenreServiceImpl(factory));
            case CHANGE_USER_BLOCKING:
                return new ChangeUserBlockingCommand(new AdminServiceImpl(factory));
            case SHOW_READERS:
                return new ShowUsersCommand(new AdminServiceImpl(factory), Role.READER);
            case SHOW_LIBRARIANS:
                return new ShowUsersCommand(new AdminServiceImpl(factory), Role.LIBRARIAN);
            case SHOW_AUTHORS:
                return new ShowAuthorsCommand(new AuthorServiceImpl(factory));
            case ADD_AUTHOR:
                return new AddAuthorCommand(new AuthorServiceImpl(factory));
            case SHOW_AUTHOR_BOOKS:
                return new ShowAuthorBooksCommand(new BookServiceImpl(factory));
            case SHOW_GENRES:
                return new ShowGenresCommand(new GenreServiceImpl(factory));
            case ADD_GENRE:
                return new AddGenreCommand(new GenreServiceImpl(factory));
            case SHOW_GENRE_BOOKS:
                return new ShowGenreBooksCommand(new BookServiceImpl(factory));
            default:
                throw new IllegalArgumentException("Unknown command = " + command);
        }
    }
}

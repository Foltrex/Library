package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.entity.Book;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowBooksCommand implements Command {

    private static final String BOOKS_PAGE = "/pages/books.jsp";

    private final BookService bookService;

    public ShowBooksCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        List<Book> books = bookService.getBooks();
        req.setAttribute("books", books);
        return CommandResult.forward(BOOKS_PAGE);
    }
}

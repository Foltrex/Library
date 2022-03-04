package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.entity.Book;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SearchBookCommand implements Command {

    private static final String BOOKS_PAGE = "/pages/books.jsp";


    private final BookService bookService;

    public SearchBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        String title = req.getParameter("bookTitle");
        List<Book> foundBooks = bookService.searchByTitle(title);
        req.setAttribute("books", foundBooks);
        return CommandResult.forward(BOOKS_PAGE);
    }
}

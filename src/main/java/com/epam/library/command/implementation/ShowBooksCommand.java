package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.entity.Book;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowBooksCommand implements Command {

    private static final int RECORDS_PER_PAGE = 3;

    private final BookService bookService;

    public ShowBooksCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        int currentPage;
        if (req.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
        } else {
            currentPage = 1;
        }

        List<Book> books = bookService.findPartOfBooks(currentPage, RECORDS_PER_PAGE);
        int totalRows = bookService.calculateBooksNumber();

        int numberOfPages = (int) Math.ceil(totalRows * 1.0 / RECORDS_PER_PAGE);

        req.setAttribute("books", books);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("numberOfPages", numberOfPages);

        return CommandResult.forward(Page.BOOKS.getName());
    }
}

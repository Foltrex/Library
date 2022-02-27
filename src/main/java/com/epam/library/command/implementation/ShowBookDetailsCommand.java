package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.entity.Book;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ShowBookDetailsCommand implements Command {

    private static final String BOOK_PAGE = "/pages/bookDetails.jsp";
    private static final String ERROR_PAGE = "/pages/errorPage.jsp";

    private final BookService service;

    public ShowBookDetailsCommand(BookService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        long id = Long.parseLong(req.getParameter("id"));

        Optional<Book> optionalBook = service.getBook(id);

        CommandResult result;
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            req.setAttribute("book", book);
            result = CommandResult.forward(BOOK_PAGE);
        } else {
            req.setAttribute("errorMessage", "Book doesn't found");
            result = CommandResult.forward(ERROR_PAGE);
        }

        return result;
    }
}

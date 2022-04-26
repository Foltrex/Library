package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.command.Paginator;
import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.extractor.RequestExtractor;
import com.epam.library.extractor.implementation.BookRequestExtractor;
import com.epam.library.service.BookService;
import com.epam.library.validator.InputDateValidator;
import com.epam.library.validator.InputStockValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SaveBookCommand implements Command {

    private final RequestExtractor<Book> requestExtractor = new BookRequestExtractor();

    private final BookService service;
    private final InputStockValidator validator;
    private final Paginator paginator;

    public SaveBookCommand(BookService service, InputStockValidator validator) {
        this.service = service;
        this.validator = validator;
        this.paginator = new Paginator(service, 4);
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        Book book = requestExtractor.extract(req);
        CommandResult result;
        if (validator.isStockValid(book.getStock())) {
            service.saveBook(book);

            int currentPage = paginator.findPageNo(req);
            List<Book> books = service.findPartOfBooks(currentPage, paginator.getRecordsPerPage());
            paginator.setPaginationParameters(req);
            req.setAttribute("books", books);

            result = CommandResult.forward(Page.BOOKS.getPath());
        } else {
            req.setAttribute("errorMessage", "Invalid stock input");
            result = CommandResult.forward(Page.INPUT_ERROR.getPath());
        }

        return result;
    }
}

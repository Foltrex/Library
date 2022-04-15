package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.entity.Book;
import com.epam.library.entity.BookRental;
import com.epam.library.entity.RentalStatus;
import com.epam.library.entity.User;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.extractor.RequestExtractor;
import com.epam.library.extractor.implementation.BookRentalRequestExtractor;
import com.epam.library.service.BookRentalService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SaveBookRentalCommand implements Command {

    private final RequestExtractor<BookRental> requestExtractor = new BookRentalRequestExtractor();

    private final BookRentalService bookRentalService;

    public SaveBookRentalCommand(BookRentalService bookRentalService) {
        this.bookRentalService = bookRentalService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        BookRental bookRental = requestExtractor.extract(req);
        bookRentalService.saveBookRental(bookRental);
        List<BookRental> bookRentals = bookRentalService.getBookRentals();
        req.setAttribute("rentals", bookRentals);
        return CommandResult.forward(Page.BOOK_RENTALS.getPath());
    }

}

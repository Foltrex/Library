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
import com.epam.library.validator.InputDateValidator;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SaveBookRentalCommand implements Command {

    private final RequestExtractor<BookRental> requestExtractor = new BookRentalRequestExtractor();

    private final BookRentalService bookRentalService;
    private final InputDateValidator validator;

    public SaveBookRentalCommand(BookRentalService bookRentalService, InputDateValidator validator) {
        this.bookRentalService = bookRentalService;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        BookRental bookRental = requestExtractor.extract(req);

        CommandResult result;
        if (validator.isDatesValid(bookRental.getBorrowDate(), bookRental.getReturnDate())) {
            bookRentalService.saveBookRental(bookRental);
            List<BookRental> bookRentals = bookRentalService.getBookRentals();
            req.setAttribute("rentals", bookRentals);
            result = CommandResult.forward(Page.BOOK_RENTALS.getPath());
        } else {
            req.setAttribute("errorMessage", "Invalid date input");
            result = CommandResult.forward(Page.INPUT_ERROR.getPath());
        }

        return result;
    }

}

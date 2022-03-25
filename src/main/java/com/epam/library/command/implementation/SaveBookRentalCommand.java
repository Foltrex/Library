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
import com.epam.library.service.BookRentalService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SaveBookRentalCommand implements Command {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private final BookRentalService bookRentalService;

    public SaveBookRentalCommand(BookRentalService bookRentalService) {
        this.bookRentalService = bookRentalService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        BookRental bookRental = extractBookRentalFromRequest(req);
        bookRentalService.saveBookRental(bookRental);
        List<BookRental> bookRentals = bookRentalService.getBookRentals();
        req.setAttribute("rentals", bookRentals);
        return CommandResult.forward(Page.BOOK_RENTALS.getName());
    }

    private BookRental extractBookRentalFromRequest(HttpServletRequest req) throws PageCommandException {
        Long id = Long.valueOf(req.getParameter("bookRentalId"));

        Long userId = Long.valueOf(req.getParameter("userId"));
        User user = User.createUserWithOnlyId(userId);

        Long bookId = Long.valueOf(req.getParameter("bookId"));
        Book book = Book.createBookWithOnlyId(bookId);

        Date borrowDate;
        Date returnDate;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

            String borrowDateString = req.getParameter("borrowDate");
            borrowDate = dateFormat.parse(borrowDateString);

            String returnDateString = req.getParameter("returnDate");
            returnDate = dateFormat.parse(returnDateString);
        } catch (ParseException e) {
            throw new PageCommandException("Uncorrect date format", e);
        }

        String rentalStatusString =  req.getParameter("rentalStatus");
        RentalStatus status = RentalStatus.valueOfStatus(rentalStatusString);

        return new BookRental(id, user, book, borrowDate, returnDate, status);
    }
}

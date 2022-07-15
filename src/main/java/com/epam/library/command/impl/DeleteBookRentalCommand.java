package com.epam.library.command.impl;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.entity.Book;
import com.epam.library.entity.BookRental;
import com.epam.library.entity.RentalStatus;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.extractor.parameter.name.BookRentalRequestParameterName;
import com.epam.library.extractor.parameter.name.BookRequestParameterName;
import com.epam.library.service.BookRentalService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/** Delete book rental from DB */
public class DeleteBookRentalCommand implements Command {

    private final BookRentalService bookRentalService;

    public DeleteBookRentalCommand(BookRentalService bookRentalService) {
        this.bookRentalService = bookRentalService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        Long bookId = Long.valueOf(req.getParameter(BookRequestParameterName.ID.getName()));
        Book book = Book.createBookWithOnlyId(bookId);

        Long bookRentalId = Long.valueOf(req.getParameter(BookRentalRequestParameterName.ID.getName()));
        String rentalStatusString =  req.getParameter(BookRentalRequestParameterName.RENTAL_STATUS.getName());
        RentalStatus status = RentalStatus.valueOfStatus(rentalStatusString);

        BookRental bookRental = BookRental.builder()
                .id(bookRentalId)
                .rentedBook(book)
                .rentalStatus(status)
                .build();

        bookRentalService.deleteBookRental(bookRental);
        List<BookRental> bookRentals = bookRentalService.getBookRentals();
        req.setAttribute("rentals", bookRentals);
        return CommandResult.forward(Page.BOOK_RENTALS.getPath());
    }
}

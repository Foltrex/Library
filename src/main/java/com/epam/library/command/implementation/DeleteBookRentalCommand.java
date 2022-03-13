package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.entity.Book;
import com.epam.library.entity.BookRental;
import com.epam.library.entity.RentalStatus;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookRentalService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteBookRentalCommand implements Command {

    private final BookRentalService bookBorrowService;

    public DeleteBookRentalCommand(BookRentalService bookBorrowService) {
        this.bookBorrowService = bookBorrowService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        Long bookId = Long.valueOf(req.getParameter("bookId"));
        Book book = Book.createBookWithOnlyId(bookId);
        Long bookRentalId = Long.valueOf(req.getParameter("bookRentalId"));
        String rentalStatusString =  req.getParameter("rentalStatus");
        RentalStatus status = RentalStatus.valueOfStatus(rentalStatusString);

        BookRental bookRental = new BookRental(bookRentalId, null, book, null, null, status);

        bookBorrowService.deleteBookRental(bookRental);
        List<BookRental> bookRentals = bookBorrowService.getBookRentals();
        req.setAttribute("rentals", bookRentals);
        return CommandResult.forward(Page.BOOK_RENTALS.getName());
    }
}

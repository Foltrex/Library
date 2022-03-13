package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.entity.BookRental;
import com.epam.library.entity.RentalStatus;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookRentalService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ShowBookRentalDetailsCommand implements Command {

    private final BookRentalService bookBorrowService;

    public ShowBookRentalDetailsCommand(BookRentalService bookBorrowService) {
        this.bookBorrowService = bookBorrowService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        long id = Long.parseLong(req.getParameter("bookRentalId"));

        Optional<BookRental> optionalBookRental = bookBorrowService.getBookRental(id);

        CommandResult result;
        if (optionalBookRental.isPresent()) {
            BookRental bookRental = optionalBookRental.get();
            req.setAttribute("bookRental", bookRental);
            req.setAttribute("rentalStatuses", RentalStatus.values());
            result = CommandResult.forward(Page.RENTAL_DETAILS.getName());
        } else {
            req.setAttribute("errorMessage", "Book Rentals doesn't found");
            result = CommandResult.forward(Page.ERROR.getName());
        }

        return result;
    }
}

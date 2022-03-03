package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.entity.Book;
import com.epam.library.entity.BookRental;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookRentalService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ChangeRentalCommand implements Command {

    private static final String ORDER_PAGE = "/pages/rentalDetails.jsp";
    private static final String ERROR_PAGE = "/pages/errorPage.jsp";

    private final BookRentalService bookRentalService;

    public ChangeRentalCommand(BookRentalService bookRentalService) {
        this.bookRentalService = bookRentalService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        long id = Long.parseLong(req.getParameter("bookRentalId"));

        Optional<BookRental> optionalBookRental = bookRentalService.getRental(id);

        CommandResult result;
        if (optionalBookRental.isPresent()) {
            BookRental bookRental = optionalBookRental.get();
            req.setAttribute("bookRental", bookRental);
            result = CommandResult.forward(ORDER_PAGE);
        } else {
            req.setAttribute("errorMessage", "Book Rentals doesn't found");
            result = CommandResult.forward(ERROR_PAGE);
        }

        return result;
    }
}

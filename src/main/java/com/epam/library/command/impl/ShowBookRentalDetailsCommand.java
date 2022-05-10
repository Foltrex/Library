package com.epam.library.command.impl;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.entity.BookRental;
import com.epam.library.entity.RentalStatus;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.extractor.parameter.name.BookRentalRequestParameterName;
import com.epam.library.service.BookRentalService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ShowBookRentalDetailsCommand implements Command {

    private final BookRentalService bookRentalService;

    public ShowBookRentalDetailsCommand(BookRentalService bookRentalService) {
        this.bookRentalService = bookRentalService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        long id = Long.parseLong(req.getParameter(BookRentalRequestParameterName.ID.getName()));

        Optional<BookRental> optionalBookRental = bookRentalService.getBookRental(id);

        CommandResult result;
        if (optionalBookRental.isPresent()) {
            BookRental bookRental = optionalBookRental.get();
            req.setAttribute("bookRental", bookRental);
            req.setAttribute("rentalStatuses", RentalStatus.values());
            result = CommandResult.forward(Page.RENTAL_DETAILS.getPath());
        } else {
            req.setAttribute("errorMessage", "Book Rentals doesn't found");
            result = CommandResult.forward(Page.ERROR.getPath());
        }

        return result;
    }
}

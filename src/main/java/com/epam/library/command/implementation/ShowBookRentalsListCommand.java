package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.entity.BookRental;
import com.epam.library.entity.RentalStatus;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookRentalService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowBookRentalsListCommand implements Command {

    private static final String RENTALS_PAGE = "/pages/booksRentals.jsp";

    private final BookRentalService rentalService;


    public ShowBookRentalsListCommand(BookRentalService rentalService) {
        this.rentalService = rentalService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        List<BookRental> rentals = rentalService.getRentals();
        req.setAttribute("rentals", rentals);
        return CommandResult.forward(RENTALS_PAGE);
    }
}

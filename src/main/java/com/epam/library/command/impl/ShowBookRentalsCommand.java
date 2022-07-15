package com.epam.library.command.impl;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.entity.BookRental;
import com.epam.library.entity.Role;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookRentalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/** Shows all book rentals */
public class ShowBookRentalsCommand implements Command {

    private final BookRentalService bookRentalService;

    public ShowBookRentalsCommand(BookRentalService bookRentalService) {
        this.bookRentalService = bookRentalService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        HttpSession session = req.getSession();
        Role userRole = (Role) session.getAttribute("userRole");

        List<BookRental> rentals;
        if (Role.READER.equals(userRole)) {
            long userId = (long) session.getAttribute("userId");
            rentals = bookRentalService.getBookRentalsForUser(userId);
        } else {
            rentals = bookRentalService.getBookRentals();
        }

        req.setAttribute("rentals", rentals);
        return CommandResult.forward(Page.BOOK_RENTALS.getPath());
    }
}

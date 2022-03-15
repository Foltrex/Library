package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.entity.BookRental;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookRentalService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowBookRentalsCommand implements Command {

    private final BookRentalService borrowService;


    public ShowBookRentalsCommand(BookRentalService borrowService) {
        this.borrowService = borrowService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        List<BookRental> rentals = borrowService.getBookRentals();
        req.setAttribute("rentals", rentals);
        return CommandResult.forward(Page.BOOK_RENTALS.getName());
    }
}
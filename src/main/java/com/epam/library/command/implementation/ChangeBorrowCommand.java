package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.entity.BookRental;
import com.epam.library.entity.RentalStatus;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookBorrowService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ChangeBorrowCommand implements Command {

    private final BookBorrowService bookBorrowService;

    public ChangeBorrowCommand(BookBorrowService bookBorrowService) {
        this.bookBorrowService = bookBorrowService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        long id = Long.parseLong(req.getParameter("bookBorrowId"));

        Optional<BookRental> optionalBookBorrow = bookBorrowService.getBorrow(id);

        CommandResult result;
        if (optionalBookBorrow.isPresent()) {
            BookRental bookBorrow = optionalBookBorrow.get();
            req.setAttribute("bookBorrow", bookBorrow);
            req.setAttribute("borrowStatuses", RentalStatus.values());
            result = CommandResult.forward(Page.RENTAL_DETAILS.getName());
        } else {
            req.setAttribute("errorMessage", "Book Borrows doesn't found");
            result = CommandResult.forward(Page.ERROR.getName());
        }

        return result;
    }
}

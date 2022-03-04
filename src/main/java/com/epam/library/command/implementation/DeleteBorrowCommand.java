package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookBorrowService;

import javax.servlet.http.HttpServletRequest;

public class DeleteBorrowCommand implements Command {

    public DeleteBorrowCommand(BookBorrowService bookRentalService) {
        this.bookRentalService = bookRentalService;
    }

    private final BookBorrowService bookRentalService;


    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        // TODO: implementation
        return null;
    }
}

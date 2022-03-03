package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookRentalService;

import javax.servlet.http.HttpServletRequest;

public class DeleteRentalCommand implements Command {

    public DeleteRentalCommand(BookRentalService bookRentalService) {
        this.bookRentalService = bookRentalService;
    }

    private final BookRentalService bookRentalService;


    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        // TODO: implementation
        return null;
    }
}

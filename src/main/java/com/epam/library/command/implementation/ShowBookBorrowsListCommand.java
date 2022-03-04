package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.entity.BookBorrow;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookBorrowService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowBookBorrowsListCommand implements Command {

    private static final String BORROWS_PAGE = "/pages/booksBorrows.jsp";

    private final BookBorrowService borrowService;


    public ShowBookBorrowsListCommand(BookBorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        List<BookBorrow> borrows = borrowService.getBorrows();
        req.setAttribute("borrows", borrows);
        return CommandResult.forward(BORROWS_PAGE);
    }
}

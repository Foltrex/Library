package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.entity.BookBorrow;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookBorrowService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteBorrowCommand implements Command {

    private static final String BORROWS_PAGE = "/pages/booksBorrows.jsp";

    private final BookBorrowService bookBorrowService;

    public DeleteBorrowCommand(BookBorrowService bookBorrowService) {
        this.bookBorrowService = bookBorrowService;
    }


    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        Long bookId = Long.valueOf(req.getParameter("bookBorrowId"));
        bookBorrowService.deleteBookBorrow(bookId);
        List<BookBorrow> bookBorrows = bookBorrowService.getBorrows();
        req.setAttribute("borrows", bookBorrows);
        return CommandResult.forward(BORROWS_PAGE);
    }
}

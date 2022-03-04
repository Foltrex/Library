package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.entity.Book;
import com.epam.library.entity.BookBorrow;
import com.epam.library.entity.BorrowStatus;
import com.epam.library.entity.User;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookBorrowService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BorrowBookCommand implements Command {

    private static final String BORROWS_PAGE = "/pages/booksBorrows.jsp";

    private final BookBorrowService service;

    public BorrowBookCommand(BookBorrowService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {

        BookBorrow bookBorrow = extractBookBorrowFromRequest(req);
        service.saveBookBorrow(bookBorrow);

        List<BookBorrow> borrows = service.getBorrows();
        req.setAttribute("borrows", borrows);
        return CommandResult.forward(BORROWS_PAGE);
    }

    private BookBorrow extractBookBorrowFromRequest(HttpServletRequest req) {
        long bookId = Long.parseLong(req.getParameter("bookId"));
        long userId = Long.parseLong(req.getParameter("userId"));

        Book bookWithOnlyId = Book.createBookWithOnlyId(bookId);
        User userWithOnlyId = User.createUserWithOnlyId(userId);

        BookBorrow bookBorrowing = BookBorrow.borrow(userWithOnlyId, bookWithOnlyId);

        return bookBorrowing;
    }
}

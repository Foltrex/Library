package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.entity.Book;
import com.epam.library.entity.BookRental;
import com.epam.library.entity.User;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookBorrowService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BorrowBookCommand implements Command {

    private final BookBorrowService service;

    public BorrowBookCommand(BookBorrowService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {

        BookRental bookBorrow = extractBookBorrowFromRequest(req);
        service.saveBookBorrow(bookBorrow);

        List<BookRental> borrows = service.getBorrows();
        req.setAttribute("borrows", borrows);
        return CommandResult.forward(Page.BOOK_RENTALS.getName());
    }

    private BookRental extractBookBorrowFromRequest(HttpServletRequest req) {
        long bookId = Long.parseLong(req.getParameter("bookId"));
        long userId = Long.parseLong(req.getParameter("userId"));

        Book bookWithOnlyId = Book.createBookWithOnlyId(bookId);
        User userWithOnlyId = User.createUserWithOnlyId(userId);

        BookRental bookBorrowing = BookRental.borrow(userWithOnlyId, bookWithOnlyId);

        return bookBorrowing;
    }
}

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SaveBookBorrowToDatabaseCommand implements Command {

    private static final String BORROWS_PAGE = "/pages/booksBorrows.jsp";

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private final BookBorrowService bookBorrowService;

    public SaveBookBorrowToDatabaseCommand(BookBorrowService bookBorrowService) {
        this.bookBorrowService = bookBorrowService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        BookBorrow bookBorrow = extractBookBorrowFromRequest(req);
        bookBorrowService.saveBookBorrow(bookBorrow);
        List<BookBorrow> bookBorrows = bookBorrowService.getBorrows();
        req.setAttribute("borrows", bookBorrows);
        return CommandResult.forward(BORROWS_PAGE);
    }

    private BookBorrow extractBookBorrowFromRequest(HttpServletRequest req) throws PageCommandException {
        Long id = Long.valueOf(req.getParameter("bookBorrowId"));

        Long userId = Long.valueOf(req.getParameter("userId"));
        User user = User.createUserWithOnlyId(userId);

        Long bookId = Long.valueOf(req.getParameter("bookId"));
        Book book = Book.createBookWithOnlyId(bookId);

        Date borrowDate;
        Date returnDate;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

            String borrowDateString = req.getParameter("borrowDate");
            borrowDate = dateFormat.parse(borrowDateString);

            String returnDateString = req.getParameter("returnDate");
            returnDate = dateFormat.parse(returnDateString);
        } catch (ParseException e) {
            throw new PageCommandException("Uncorrect date format", e);
        }

        String borrowStatusString =  req.getParameter("borrowStatus");
        BorrowStatus status = BorrowStatus.valueOfStatus(borrowStatusString);

        return new BookBorrow(id, user, book, borrowDate, returnDate, status);
    }
}

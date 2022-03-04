package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.entity.BookBorrow;
import com.epam.library.entity.BorrowStatus;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookBorrowService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ChangeBorrowCommand implements Command {

    private static final String ORDER_PAGE = "/pages/borrowDetails.jsp";
    private static final String ERROR_PAGE = "/pages/errorPage.jsp";

    private final BookBorrowService bookBorrowService;

    public ChangeBorrowCommand(BookBorrowService bookBorrowService) {
        this.bookBorrowService = bookBorrowService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        long id = Long.parseLong(req.getParameter("bookBorrowId"));

        Optional<BookBorrow> optionalBookBorrow = bookBorrowService.getBorrow(id);

        CommandResult result;
        if (optionalBookBorrow.isPresent()) {
            BookBorrow bookBorrow = optionalBookBorrow.get();
            req.setAttribute("bookBorrow", bookBorrow);
            req.setAttribute("borrowStatuses", BorrowStatus.values());
            result = CommandResult.forward(ORDER_PAGE);
        } else {
            req.setAttribute("errorMessage", "Book Borrows doesn't found");
            result = CommandResult.forward(ERROR_PAGE);
        }

        return result;
    }
}

package com.epam.library.extractor.impl;

import com.epam.library.entity.*;
import com.epam.library.extractor.AbstractRequestExtractor;
import com.epam.library.extractor.parameter.name.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Extracts {@link com.epam.library.entity.BookRental} rental object from request */
public class BookRentalRequestExtractor extends AbstractRequestExtractor<BookRental> {
    private static final Logger LOGGER = LogManager.getLogger(BookRentalRequestExtractor.class);

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public BookRental extract(HttpServletRequest request) {
        String bookRentalIdString = request.getParameter(BookRentalRequestParameterName.ID.getName());
        Long bookRentalId = super.isParsable(bookRentalIdString) ? Long.valueOf(bookRentalIdString) : null;
        User bookRentalUser = extractBookRentalUser(request);
        Book bookRentalBook = extractBookRentalBook(request);

        Date bookRentalBorrowDate = null;
        Date bookRentalReturnDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

            String bookRentalBorrowDateString = request.getParameter(BookRentalRequestParameterName.BORROW_DATE.getName());
            bookRentalBorrowDate = super.isParsable(bookRentalBorrowDateString) ? dateFormat.parse(bookRentalBorrowDateString) : null;

            String bookRentalReturnDateString = request.getParameter(BookRentalRequestParameterName.RETURN_DATE.getName());
            bookRentalReturnDate = super.isParsable(bookRentalReturnDateString) ? dateFormat.parse(bookRentalReturnDateString) : null;
        } catch (ParseException e) {
            LOGGER.warn("Invalid borrow or return date formats", e);
        }

        String bookRentalRentalStatusString = request.getParameter(BookRentalRequestParameterName.RENTAL_STATUS.getName());
        RentalStatus bookRentalRentalStatus = super.isParsable(bookRentalRentalStatusString)
                ? RentalStatus.valueOfStatus(bookRentalRentalStatusString)
                : RentalStatus.WAITING_FOR_ISSUANCE;

        return BookRental.builder()
                .id(bookRentalId)
                .user(bookRentalUser)
                .rentedBook(bookRentalBook)
                .borrowDate(bookRentalBorrowDate)
                .returnDate(bookRentalReturnDate)
                .rentalStatus(bookRentalRentalStatus)
                .build();
    }


    private Book extractBookRentalBook(HttpServletRequest request) {
        String bookRentalBookIdString = request.getParameter(BookRequestParameterName.ID.getName());
        Long bookRentalBookId = super.isParsable(bookRentalBookIdString) ? Long.valueOf(bookRentalBookIdString) : null;

        String bookRentalBookTitle = request.getParameter(BookRequestParameterName.TITLE.getName());

        Author bookRentalBookAuthor = extractBookRentalAuthor(request);

        String bookRentalBookStockString = request.getParameter(BookRequestParameterName.STOCK.getName());
        int bookRentalBookStock = super.isParsable(bookRentalBookStockString) ? Integer.parseInt(bookRentalBookStockString) : 0;

        Genre bookRentalBookGenre = extractBookRentalGenre(request);

        return Book.builder()
                .id(bookRentalBookId)
                .title(bookRentalBookTitle)
                .author(bookRentalBookAuthor)
                .stock(bookRentalBookStock)
                .genre(bookRentalBookGenre)
                .build();
    }

    private User extractBookRentalUser(HttpServletRequest request) {
        String bookRentalUserIdString = request.getParameter(UserRequestParameterName.ID.getName());
        Long bookRentalUserId = super.isParsable(bookRentalUserIdString) ? Long.valueOf(bookRentalUserIdString) : null;
        String bookRentalUserName = request.getParameter(UserRequestParameterName.NAME.getName());
        String bookRentalUserSurname = request.getParameter(UserRequestParameterName.SURNAME.getName());
        String bookRentalUserPhoneNumber = request.getParameter(UserRequestParameterName.PHONE_NUMBER.getName());
        String bookRentalUserLogin = request.getParameter(UserRequestParameterName.LOGIN.getName());

        String bookRentalUserRoleString = request.getParameter(UserRequestParameterName.ROLE.getName());
        Role bookRentalUserRole = Role.valueOfRoleName(bookRentalUserRoleString);

        String bookRentalUserIsBannedString = request.getParameter(UserRequestParameterName.IS_BANNED.getName());
        boolean bookRentalUserIsBanned = Boolean.parseBoolean(bookRentalUserIsBannedString);

        return User.builder()
                .id(bookRentalUserId)
                .name(bookRentalUserName)
                .surname(bookRentalUserSurname)
                .phoneNumber(bookRentalUserPhoneNumber)
                .login(bookRentalUserLogin)
                .role(bookRentalUserRole)
                .isBanned(bookRentalUserIsBanned)
                .build();
    }

    private Author extractBookRentalAuthor(HttpServletRequest request) {
        String bookRentalBookAuthorName = request.getParameter(AuthorRequestParameterName.NAME.getName());
        String bookRentalBookAuthorSurname = request.getParameter(AuthorRequestParameterName.SURNAME.getName());

        return Author.builder()
                .name(bookRentalBookAuthorName)
                .surname(bookRentalBookAuthorSurname)
                .build();
    }

    private Genre extractBookRentalGenre(HttpServletRequest request) {
        String bookRentalBookGenreName = request.getParameter(GenreRequestParameterName.NAME.getName());

        return Genre.builder()
                .name(bookRentalBookGenreName)
                .build();
    }
}

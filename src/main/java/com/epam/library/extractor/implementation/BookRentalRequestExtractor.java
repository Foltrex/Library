package com.epam.library.extractor.implementation;

import com.epam.library.Controller;
import com.epam.library.entity.*;
import com.epam.library.extractor.RequestExtractor;
import com.epam.library.extractor.parameter.name.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookRentalRequestExtractor implements RequestExtractor<BookRental> {
    private static final Logger LOGGER = LogManager.getLogger(BookRentalRequestExtractor.class);

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public BookRental extract(HttpServletRequest request) {
        String bookRentalIdString = request.getParameter(BookRentalRequestParameterName.ID.getName());
        Long bookRentalId = (bookRentalIdString) != null ? Long.valueOf(bookRentalIdString) : null;

        String bookRentalUserIdString = request.getParameter(UserRequestParameterName.ID.getName());
        Long bookRentalUserId = (bookRentalUserIdString != null) ? Long.valueOf(bookRentalUserIdString) : null;
        String bookRentalUserName = request.getParameter(UserRequestParameterName.NAME.getName());
        String bookRentalUserSurname = request.getParameter(UserRequestParameterName.SURNAME.getName());
        String bookRentalUserPhoneNumber = request.getParameter(UserRequestParameterName.PHONE_NUMBER.getName());
        String bookRentalUserLogin = request.getParameter(UserRequestParameterName.LOGIN.getName());

        String bookRentalUserRoleString = request.getParameter(UserRequestParameterName.ROLE.getName());
        Role bookRentalUserRole = Role.valueOfRoleName(bookRentalUserRoleString);

        String bookRentalUserIsBannedString = request.getParameter(UserRequestParameterName.IS_BANNED.getName());
        boolean bookRentalUserIsBanned = Boolean.parseBoolean(bookRentalUserIsBannedString);

        User bookRentalUser = new User(bookRentalUserId, bookRentalUserName, bookRentalUserSurname, bookRentalUserPhoneNumber,
                bookRentalUserLogin, null, bookRentalUserRole, bookRentalUserIsBanned);

        String bookRentalBookIdString = request.getParameter(BookRequestParameterName.ID.getName());
        Long bookRentalBookId = (bookRentalBookIdString != null) ? Long.valueOf(bookRentalBookIdString) : null;
        String bookRentalBookTitle = request.getParameter(BookRequestParameterName.TITLE.getName());
        String bookRentalBookAuthorName = request.getParameter(AuthorRequestParameterName.NAME.getName());
        String bookRentalBookAuthorSurname = request.getParameter(AuthorRequestParameterName.SURNAME.getName());
        Author bookRentalBookAuthor = new Author(null, bookRentalBookAuthorName, bookRentalBookAuthorSurname);

        String bookRentalBookStockString = request.getParameter(BookRequestParameterName.STOCK.getName());
        int bookRentalBookStock = (bookRentalBookStockString != null) ? Integer.parseInt(bookRentalBookStockString) : 0;

        String bookRentalBookGenreName = request.getParameter(GenreRequestParameterName.NAME.getName());
        Genre bookRentalBookGenre = new Genre(null, bookRentalBookGenreName);
        Book bookRentalBook = new Book(bookRentalBookId, bookRentalBookTitle, bookRentalBookAuthor, bookRentalBookStock, bookRentalBookGenre);

        Date bookRentalBorrowDate = null;
        Date bookRentalReturnDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

            String bookRentalBorrowDateString = request.getParameter(BookRentalRequestParameterName.BORROW_DATE.getName());
            bookRentalBorrowDate = (bookRentalBorrowDateString != null) ? dateFormat.parse(bookRentalBorrowDateString) : null;

            String bookRentalReturnDateString = request.getParameter(BookRentalRequestParameterName.RETURN_DATE.getName());
            bookRentalReturnDate = (bookRentalReturnDateString != null) ? dateFormat.parse(bookRentalReturnDateString) : null;
        } catch (ParseException e) {
            LOGGER.warn("Invalid borrow or return date formats", e);
        }

        String bookRentalRentalStatusString = request.getParameter(BookRentalRequestParameterName.RENTAL_STATUS.getName());
        RentalStatus bookRentalRentalStatus = (bookRentalRentalStatusString != null)
                ? RentalStatus.valueOfStatus(bookRentalRentalStatusString)
                : RentalStatus.WAITING_FOR_ISSUANCE;

        return new BookRental(bookRentalId, bookRentalUser, bookRentalBook, bookRentalBorrowDate, bookRentalReturnDate, bookRentalRentalStatus);
    }
}

package com.epam.library.entity;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Value
@Builder
public class BookRental implements Identifable, Serializable {

    public static final String TABLE = "borrows";

    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String USER_LOGIN = "user_login";
    public static final String BOOK_ID = "book_id";
    public static final String BOOK_TITLE = "book_title";
    public static final String BORROW_DATE = "borrow_date";
    public static final String RETURN_DATE = "return_date";
    public static final String STATUS = "status";

    Long id;
    User user;
    Book rentedBook;
    Date borrowDate;
    Date returnDate;
    RentalStatus rentalStatus;


    public static BookRental borrow(User user, Book book) {
        return new BookRental(null, user, book, null, null, RentalStatus.WAITING_FOR_ISSUANCE);
    }
}

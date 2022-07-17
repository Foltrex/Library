package com.epam.library.mapper.impl;

import com.epam.library.entity.Book;
import com.epam.library.entity.BookRental;
import com.epam.library.entity.RentalStatus;
import com.epam.library.entity.User;
import com.epam.library.mapper.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Maps result set to {@link com.epam.library.entity.BookRental} */
public class BookRentalRowMapper implements RowMapper<BookRental> {
    @Override
    public BookRental map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(BookRental.ID);

        Long userId = resultSet.getLong(BookRental.USER_ID);
        String userLogin = resultSet.getString(BookRental.USER_LOGIN);
        User userWithIdAndLogin = User.builder()
                .id(userId)
                .login(userLogin)
                .build();

        Long bookId = resultSet.getLong(BookRental.BOOK_ID);
        String bookTitle = resultSet.getString(BookRental.BOOK_TITLE);
        Book bookWithIdAndTitle = Book.builder()
                .id(bookId)
                .title(bookTitle)
                .build();

        Date borrowDate = resultSet.getDate(BookRental.BORROW_DATE);
        Date returnDate = resultSet.getDate(BookRental.RETURN_DATE);

        String statusString = resultSet.getString(BookRental.STATUS);
        RentalStatus status = RentalStatus.valueOfStatus(statusString);

        return BookRental.builder()
                .id(id)
                .user(userWithIdAndLogin)
                .rentedBook(bookWithIdAndTitle)
                .borrowDate(borrowDate)
                .returnDate(returnDate)
                .rentalStatus(status)
                .build();
    }
}

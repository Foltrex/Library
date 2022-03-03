package com.epam.library.mapper;

import com.epam.library.entity.Book;
import com.epam.library.entity.BookRental;
import com.epam.library.entity.RentalStatus;
import com.epam.library.entity.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRentalRowMapper implements RowMapper<BookRental> {
    @Override
    public BookRental map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(BookRental.ID);

        Long userId = resultSet.getLong(BookRental.USER_ID);
        String userLogin = resultSet.getString(BookRental.USER_LOGIN);
        User userWithIdAndLogin = User.createUserWithIDAndLogin(userId, userLogin);

        Long bookId = resultSet.getLong(BookRental.BOOK_ID);
        String bookTitle = resultSet.getString(BookRental.BOOK_TITLE);
        Book bookWithIdAndTitle = Book.createBookWithIDAndTitle(bookId, bookTitle);

        Date borrowDate = resultSet.getDate(BookRental.BORROW_DATE);
        Date returnDate = resultSet.getDate(BookRental.RETURN_DATE);

        String statusString = resultSet.getString(BookRental.STATUS);
        RentalStatus status = RentalStatus.valueOfStatus(statusString);

        return new BookRental(id, userWithIdAndLogin, bookWithIdAndTitle, borrowDate, returnDate, status);
    }
}

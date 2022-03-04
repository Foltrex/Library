package com.epam.library.mapper;

import com.epam.library.entity.Book;
import com.epam.library.entity.BookBorrow;
import com.epam.library.entity.BorrowStatus;
import com.epam.library.entity.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookBorrowRowMapper implements RowMapper<BookBorrow> {
    @Override
    public BookBorrow map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(BookBorrow.ID);

        Long userId = resultSet.getLong(BookBorrow.USER_ID);
        String userLogin = resultSet.getString(BookBorrow.USER_LOGIN);
        User userWithIdAndLogin = User.createUserWithIDAndLogin(userId, userLogin);

        Long bookId = resultSet.getLong(BookBorrow.BOOK_ID);
        String bookTitle = resultSet.getString(BookBorrow.BOOK_TITLE);
        Book bookWithIdAndTitle = Book.createBookWithIDAndTitle(bookId, bookTitle);

        Date borrowDate = resultSet.getDate(BookBorrow.BORROW_DATE);
        Date returnDate = resultSet.getDate(BookBorrow.RETURN_DATE);

        String statusString = resultSet.getString(BookBorrow.STATUS);
        BorrowStatus status = BorrowStatus.valueOfStatus(statusString);

        return new BookBorrow(id, userWithIdAndLogin, bookWithIdAndTitle, borrowDate, returnDate, status);
    }
}

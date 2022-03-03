package com.epam.library.dao.implementation;

import com.epam.library.dao.AbstractDao;
import com.epam.library.dao.BookRentalDao;
import com.epam.library.entity.BookRental;
import com.epam.library.exception.DaoException;
import com.epam.library.mapper.BookRentalRowMapper;
import com.epam.library.mapper.RowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class BookRentalDaoImpl extends AbstractDao<BookRental> implements BookRentalDao {

    private static final String SELECT_ORDERS = String.format("%s %s %s %s %s",
            "SELECT rentals.id, user_id, users.login AS user_login,",
            "book_id, books.title AS book_title, borrow_date, return_date, status",
            "FROM rentals",
            "INNER JOIN users ON user_id = users.id",
            "INNER JOIN books ON book_id = books.id;");


    public BookRentalDaoImpl(Connection connection) {
        super(connection, new BookRentalRowMapper(), BookRental.TABLE);
    }

    // TODO: implementation
    @Override
    protected Map<String, Object> extractFields(BookRental item) {
        return null;
    }

    @Override
    public List<BookRental> getBooksRentals() throws DaoException {
        return executeQuery(SELECT_ORDERS);
    }
}

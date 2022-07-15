package com.epam.library.dao.impl;

import com.epam.library.dao.AbstractDao;
import com.epam.library.dao.BookRentalDao;
import com.epam.library.entity.BookRental;
import com.epam.library.exception.DaoException;
import com.epam.library.mapper.impl.BookRentalRowMapper;
import com.google.common.collect.ImmutableMap;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BookRentalDaoImpl extends AbstractDao<BookRental> implements BookRentalDao {

    private static final String SELECT_ORDERS = String.format("%s %s %s %s %s ",
            "SELECT borrows.id, user_id, users.login AS user_login,",
            "book_id, books.title AS book_title, borrow_date, return_date, status",
            "FROM borrows",
            "INNER JOIN users ON user_id = users.id",
            "INNER JOIN books ON book_id = books.id");

    private static final String SELECT_ORDERS_BY_USER_ID = String.format("%s WHERE user_id = ?", SELECT_ORDERS);


    public BookRentalDaoImpl(Connection connection) {
        super(connection, new BookRentalRowMapper(), BookRental.TABLE);
    }


    @Override
    protected Map<String, Object> extractFields(BookRental item) {
        return ImmutableMap.of(
                BookRental.USER_ID, item.getUser().getId(),
                BookRental.BOOK_ID, item.getRentedBook().getId(),
                BookRental.BORROW_DATE, item.getBorrowDate(),
                BookRental.RETURN_DATE, item.getReturnDate(),
                BookRental.STATUS, item.getRentalStatus().getStatusName()
        );
    }

    @Override
    public List<BookRental> getBooksRentals() throws DaoException {
        return executeQuery(SELECT_ORDERS);
    }

    @Override
    public List<BookRental> getBooksRentalsForUser(long id) throws DaoException {
        return executeQuery(SELECT_ORDERS_BY_USER_ID, id);
    }

    @Override
    public void deleteBookRental(long id) throws DaoException {
        removeById(id);
    }

    @Override
    public void saveBookRental(BookRental bookRental) throws DaoException {
        save(bookRental);
    }

    @Override
    public Optional<BookRental> findBookRentalById(long id) throws DaoException {
        String condition = String.format("WHERE %s.id = ?", BookRental.TABLE);
        return executeForSingleResult(SELECT_ORDERS + condition, id);
    }
}

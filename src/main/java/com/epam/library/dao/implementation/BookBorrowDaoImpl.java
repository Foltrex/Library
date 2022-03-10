package com.epam.library.dao.implementation;

import com.epam.library.dao.AbstractDao;
import com.epam.library.dao.BookBorrowDao;
import com.epam.library.entity.BookRental;
import com.epam.library.exception.DaoException;
import com.epam.library.mapper.BookBorrowRowMapper;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BookBorrowDaoImpl extends AbstractDao<BookRental> implements BookBorrowDao {

    private static final String SELECT_ORDERS = String.format("%s %s %s %s %s ",
            "SELECT borrows.id, user_id, users.login AS user_login,",
            "book_id, books.title AS book_title, borrow_date, return_date, status",
            "FROM borrows",
            "INNER JOIN users ON user_id = users.id",
            "INNER JOIN books ON book_id = books.id");


    public BookBorrowDaoImpl(Connection connection) {
        super(connection, new BookBorrowRowMapper(), BookRental.TABLE);
    }


    @Override
    protected Map<String, Object> extractFields(BookRental item) {
        // ImmutableMap.of (...) is more beautiful :(
        return new LinkedHashMap<String, Object>() {{
            put(BookRental.USER_ID, item.getUser().getId());
            put(BookRental.BOOK_ID, item.getRentedBook().getId());
            put(BookRental.BORROW_DATE, item.getBorrowDate());
            put(BookRental.RETURN_DATE, item.getReturnDate());
            put(BookRental.STATUS, item.getBorrowStatus().getStatusName());
        }};
    }

    @Override
    public List<BookRental> getBooksBorrows() throws DaoException {
        return executeQuery(SELECT_ORDERS);
    }

    @Override
    public void deleteBookBorrow(long id) throws DaoException {
        removeById(id);
    }

    @Override
    public void saveBookBorrow(BookRental bookRental) throws DaoException {
        save(bookRental);
    }

    @Override
    public Optional<BookRental> findBookBorrowById(long id) throws DaoException {
        String condition = String.format("WHERE %s.id = ?", BookRental.TABLE);
        return executeForSingleResult(SELECT_ORDERS + condition, id);
    }
}

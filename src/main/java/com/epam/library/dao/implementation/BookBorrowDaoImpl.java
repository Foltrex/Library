package com.epam.library.dao.implementation;

import com.epam.library.dao.AbstractDao;
import com.epam.library.dao.BookBorrowDao;
import com.epam.library.entity.BookBorrow;
import com.epam.library.exception.DaoException;
import com.epam.library.mapper.BookBorrowRowMapper;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BookBorrowDaoImpl extends AbstractDao<BookBorrow> implements BookBorrowDao {

    private static final String SELECT_ORDERS = String.format("%s %s %s %s %s ",
            "SELECT borrows.id, user_id, users.login AS user_login,",
            "book_id, books.title AS book_title, borrow_date, return_date, status",
            "FROM borrows",
            "INNER JOIN users ON user_id = users.id",
            "INNER JOIN books ON book_id = books.id");


    public BookBorrowDaoImpl(Connection connection) {
        super(connection, new BookBorrowRowMapper(), BookBorrow.TABLE);
    }

    // TODO: implementation
    @Override
    protected Map<String, Object> extractFields(BookBorrow item) {
        // ImmutableMap.of (...) is more beautiful :(
        return new LinkedHashMap<String, Object>() {{
            put(BookBorrow.USER_ID, item.getUser().getId());
            put(BookBorrow.BOOK_ID, item.getRentedBook().getId());
            put(BookBorrow.BORROW_DATE, item.getBorrowDate());
            put(BookBorrow.RETURN_DATE, item.getReturnDate());
            put(BookBorrow.STATUS, item.getBorrowStatus().getStatusName());
        }};
    }

    @Override
    public List<BookBorrow> getBooksBorrows() throws DaoException {
        return executeQuery(SELECT_ORDERS);
    }

    @Override
    public void deleteBookBorrow(long id) throws DaoException {
        removeById(id);
    }

    @Override
    public void saveBookBorrow(BookBorrow bookRental) throws DaoException {
        save(bookRental);
    }

    @Override
    public Optional<BookBorrow> findBookBorrowById(long id) throws DaoException {
        String condition = String.format("WHERE %s.id = ?", BookBorrow.TABLE);
        return executeForSingleResult(SELECT_ORDERS + condition, id);
    }
}

package com.epam.library.dao.implementation;

import com.epam.library.dao.AbstractDao;
import com.epam.library.dao.BookDao;
import com.epam.library.entity.Book;
import com.epam.library.exception.DaoException;
import com.epam.library.mapper.BookRowMapper;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BookDaoImpl extends AbstractDao<Book> implements BookDao {

    public BookDaoImpl(Connection connection) {
        super(connection, new BookRowMapper(), Book.TABLE);
    }

    @Override
    public List<Book> getBooks() throws DaoException {
        return getAll();
    }

    @Override
    public Optional<Book> findBookById(long id) throws DaoException {
        return getById(id);
    }


    @Override
    protected Map<String, Object> extractFields(Book item) {
        // ImmutableMap.of (...) is more beautiful :(
        return new LinkedHashMap<String, Object>() {{
            put(Book.ID, item.getId());
            put(Book.TITLE, item.getTitle());
            put(Book.AUTHOR_ID, item.getAuthorId());
            put(Book.STOCK, item.getStock());
            put(Book.GENRE_ID, item.getGenreId());
        }};
    }
}

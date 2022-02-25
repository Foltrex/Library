package com.epam.library.dao.implementation;

import com.epam.library.dao.AbstractDao;
import com.epam.library.entity.Book;
import com.epam.library.mapper.BookRowMapper;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class BookDaoImpl extends AbstractDao<Book> {

    public BookDaoImpl(Connection connection) {
        super(connection, new BookRowMapper(), Book.TABLE);
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

package com.epam.library.dao.implemetation;

import com.epam.library.dao.AbstractDao;
import com.epam.library.entity.Book;
import com.epam.library.mapper.BookRowMapper;
import com.google.common.collect.ImmutableMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class BookDaoImpl extends AbstractDao<Book> {

    public static final String TABLE = "books";

    public static final String TITLE = "title";
    public static final String AUTHOR_ID = "author_id";
    public static final String STOCK = "stock";
    public static final String GENRE_ID = "genre_id";

    private static final String SAVE_BOOK = "INSERT INTO books(title, stock, genre_id) VALUES(?,?,?);";

    public BookDaoImpl(Connection connection) {
        super(connection, new BookRowMapper(), TABLE);
    }

    @Override
    protected Map<String, Object> extractFields(Book item) {
        return ImmutableMap.of(
                ID,         item.getId(),
                TITLE,      item.getTitle(),
                AUTHOR_ID,  item.getAuthorId(),
                STOCK,      item.getStock(),
                GENRE_ID,   item.getGenreId()
        );
    }
}

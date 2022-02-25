package com.epam.library.mapper;

import com.epam.library.dao.implementation.BookDaoImpl;
import com.epam.library.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(Book.ID);
        String title = resultSet.getString(Book.TITLE);
        Long authorId = resultSet.getLong(Book.AUTHOR_ID);
        int stock = resultSet.getInt(Book.STOCK);
        long genreId = resultSet.getLong(Book.GENRE_ID);

        return new Book(id, title, authorId, stock, genreId);
    }
}

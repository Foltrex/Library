package com.epam.library.mapper;

import com.epam.library.dao.implemetation.BookDaoImpl;
import com.epam.library.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(BookDaoImpl.ID);
        String title = resultSet.getString(BookDaoImpl.TITLE);
        Long authorId = resultSet.getLong(BookDaoImpl.AUTHOR_ID);
        int stock = resultSet.getInt(BookDaoImpl.STOCK);
        long genreId = resultSet.getLong(BookDaoImpl.GENRE_ID);

        return new Book(id, title, authorId, stock, genreId);
    }
}

package com.epam.library.mapper;

import com.epam.library.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String title = resultSet.getString("title");
        int stock = resultSet.getInt("stock");
        long genreId = resultSet.getLong("genre_id");

        return new Book(id, title, stock, genreId);
    }
}

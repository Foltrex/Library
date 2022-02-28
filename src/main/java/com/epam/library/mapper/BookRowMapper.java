package com.epam.library.mapper;

import com.epam.library.dao.implementation.BookDaoImpl;
import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Book.ID);
        String title = resultSet.getString(Book.TITLE);

        Long authorId = resultSet.getLong(Book.AUTHOR_ID);
        String authorName = resultSet.getString(Book.AUTHOR_NAME);
        String authorSurname = resultSet.getString(Book.AUTHOR_SURNAME);
        Author author = new Author(authorId, authorName, authorSurname);

        int stock = resultSet.getInt(Book.STOCK);

        Long genreId = resultSet.getLong(Book.GENRE_ID);
        String genreName = resultSet.getString(Book.GENRE_NAME);
        Genre genre = new Genre(genreId, genreName);

        return new Book(id, title, author, stock, genre);
    }
}

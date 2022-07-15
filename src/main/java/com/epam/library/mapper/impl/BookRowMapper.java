package com.epam.library.mapper.impl;

import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;
import com.epam.library.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/** Maps result set to {@link com.epam.library.entity.Book} */
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

        return Book.builder()
                .id(id)
                .title(title)
                .author(author)
                .stock(stock)
                .genre(genre)
                .build();
    }
}

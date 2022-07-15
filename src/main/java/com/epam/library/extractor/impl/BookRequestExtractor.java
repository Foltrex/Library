package com.epam.library.extractor.impl;

import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;
import com.epam.library.extractor.AbstractRequestExtractor;
import com.epam.library.extractor.parameter.name.AuthorRequestParameterName;
import com.epam.library.extractor.parameter.name.BookRequestParameterName;
import com.epam.library.extractor.parameter.name.GenreRequestParameterName;

import javax.servlet.http.HttpServletRequest;

/** Extracts {@link com.epam.library.entity.Book} object from request */
public class BookRequestExtractor extends AbstractRequestExtractor<Book> {


    @Override
    public Book extract(HttpServletRequest request) {
        String bookIdString = request.getParameter(BookRequestParameterName.ID.getName());
        String bookAuthorIdString = request.getParameter(AuthorRequestParameterName.ID.getName());
        String bookGenreIdString = request.getParameter(GenreRequestParameterName.ID.getName());

        Long bookId = super.isParsable(bookIdString) ? Long.valueOf(bookIdString) : null;

        String bookTitle = request.getParameter(BookRequestParameterName.TITLE.getName());

        Long bookAuthorId = super.isParsable(bookAuthorIdString) ? Long.valueOf(bookAuthorIdString) : null;
        String bookAuthorName = request.getParameter(AuthorRequestParameterName.NAME.getName());
        String bookAuthorSurname = request.getParameter(AuthorRequestParameterName.SURNAME.getName());
        Author bookAuthor = new Author(bookAuthorId, bookAuthorName, bookAuthorSurname);

        String bookStockString = request.getParameter(BookRequestParameterName.STOCK.getName());
        int bookStock = super.isParsable(bookStockString) ? Integer.parseInt(bookStockString) : 0;

        Long bookGenreId = super.isParsable(bookGenreIdString) ? Long.valueOf(bookGenreIdString) : null;
        String bookGenreName = request.getParameter(GenreRequestParameterName.NAME.getName());
        Genre bookGenre = new Genre(bookGenreId, bookGenreName);

        return Book.builder()
                .id(bookId)
                .title(bookTitle)
                .author(bookAuthor)
                .stock(bookStock)
                .genre(bookGenre)
                .build();
    }
}

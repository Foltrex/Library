package com.epam.library.extractor.implementation;

import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;
import com.epam.library.extractor.RequestExtractor;
import com.epam.library.extractor.parameter.name.AuthorRequestParameterName;
import com.epam.library.extractor.parameter.name.BookRequestParameterName;
import com.epam.library.extractor.parameter.name.GenreRequestParameterName;

import javax.servlet.http.HttpServletRequest;

public class BookRequestExtractor implements RequestExtractor<Book> {


    @Override
    public Book extract(HttpServletRequest request) {
        String bookIdString = request.getParameter(BookRequestParameterName.ID.getName());
        String bookAuthorIdString = request.getParameter(AuthorRequestParameterName.ID.getName());
        String bookGenreIdString = request.getParameter(GenreRequestParameterName.ID.getName());

        Long bookId = (bookIdString != null) ? Long.valueOf(bookIdString) : null;

        String bookTitle = request.getParameter(BookRequestParameterName.TITLE.getName());

        Long bookAuthorId = (bookAuthorIdString != null) ? Long.valueOf(bookAuthorIdString) : null;
        String bookAuthorName = request.getParameter(AuthorRequestParameterName.NAME.getName());
        String bookAuthorSurname = request.getParameter(AuthorRequestParameterName.SURNAME.getName());
        Author bookAuthor = new Author(bookAuthorId, bookAuthorName, bookAuthorSurname);

        String bookStockString = request.getParameter(BookRequestParameterName.STOCK.getName());
        int bookStock = (bookStockString != null) ? Integer.parseInt(bookStockString) : 0;

        Long bookGenreId = (bookGenreIdString != null) ? Long.valueOf(bookGenreIdString) : null;
        String bookGenreName = request.getParameter(GenreRequestParameterName.NAME.getName());
        Genre bookGenre = new Genre(bookGenreId, bookGenreName);

        return new Book(bookId, bookTitle, bookAuthor, bookStock, bookGenre);
    }
}

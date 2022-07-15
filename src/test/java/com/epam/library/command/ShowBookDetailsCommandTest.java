package com.epam.library.command;

import com.epam.library.command.impl.ShowBookDetailsCommand;
import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.AuthorService;
import com.epam.library.service.BookService;
import com.epam.library.service.GenreService;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ShowBookDetailsCommandTest {

    private final List<Author> authors = ImmutableList.of(
            new Author(1L, "Alex", "Pushkin"),
            new Author(2L, "Leo", "Tolstoy")
    );

    private final List<Genre> genres = ImmutableList.of(
            new Genre(1L, "Russian Classic")
    );

    private final Optional<Book> optionalBook = Optional.of(
            Book.builder()
                    .id(1L)
                    .title("War and Peace")
                    .author(new Author(2L, "Leo", "Tolstoy"))
                    .stock(2)
                    .genre(new Genre(1L, "Russian Classic"))
                    .build()
    );

    @Test
    public void testExecuteShouldShowBookDetailsWhenBookIdIsGiven() throws ServiceException, PageCommandException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("bookId")).thenReturn("1");

        BookService bookService = mock(BookService.class);
        AuthorService authorService = mock(AuthorService.class);
        GenreService genreService = mock(GenreService.class);

        when(bookService.searchBookById(anyLong())).thenReturn(optionalBook);
        when(authorService.getAuthors()).thenReturn(authors);
        when(genreService.getGenres()).thenReturn(genres);

        doNothing().when(request).setAttribute(anyString(), any(Book.class));
        doNothing().when(request).setAttribute(anyString(), anyList());

        ShowBookDetailsCommand command = new ShowBookDetailsCommand(bookService, authorService, genreService);

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(bookService, times(1)).searchBookById(anyLong());
        verify(authorService, times(1)).getAuthors();
        verify(genreService, times(1)).getGenres();
        verify(request, times(1)).setAttribute(anyString(), any(Book.class));
        verify(request, times(2)).setAttribute(anyString(), anyList());
    }
}

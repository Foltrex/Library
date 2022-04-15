package com.epam.library.command;

import com.epam.library.command.implementation.ShowGenreBooksCommand;
import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookService;
import org.junit.Test;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class ShowGenreBooksCommandTest {

    private final List<Book> books = Arrays.asList(
            new Book(2L, "War and Peace", Author.createAuthorWithOnlyIDField(1L), 2, Genre.createGenreWithOnlyIDField(1L)),
            new Book(2L, "Resurrection", Author.createAuthorWithOnlyIDField(1L), 2, Genre.createGenreWithOnlyIDField(1L))
    );

    @Test
    public void testExecuteShouldShowGenreBooksWhenGenreIsGiven() throws ServiceException, PageCommandException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("genreId")).thenReturn("1");
        when(request.getParameter("genreName")).thenReturn("Russian Classic");
        when(request.getParameter("pageNo")).thenReturn("1");

        BookService bookService = mock(BookService.class);
        when(bookService.searchBooksFromPositionByGenreId(anyLong(), anyInt(), anyInt())).thenReturn(books);
        doNothing().when(request).setAttribute(anyString(), anyInt());
        doNothing().when(request).setAttribute(anyString(), anyList());
        doNothing().when(request).setAttribute(anyString(), any(Genre.class));

        ShowGenreBooksCommand command = new ShowGenreBooksCommand(bookService);

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(bookService, times(1)).searchBooksFromPositionByGenreId(anyLong(), anyInt(), anyInt());
        verify(request, times(3)).setAttribute(anyString(), anyInt());
        verify(request, times(1)).setAttribute(anyString(), anyList());
        verify(request, times(1)).setAttribute(anyString(), any(Genre.class));
    }
}

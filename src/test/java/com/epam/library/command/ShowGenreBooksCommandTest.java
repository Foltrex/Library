package com.epam.library.command;

import com.epam.library.command.impl.ShowGenreBooksCommand;
import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookService;
import com.google.common.collect.ImmutableList;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class ShowGenreBooksCommandTest {

    private final List<Book> books = ImmutableList.of(
            Book.builder()
                    .id(2L)
                    .title("War and Peace")
                    .author(Author.builder().id(1L).build())
                    .stock(2)
                    .genre(Genre.builder().id(1L).build())
                    .build(),
            Book.builder()
                    .id(2L)
                    .title("Resurrection")
                    .author(Author.builder().id(1L).build())
                    .stock(2)
                    .genre(Genre.builder().id(1L).build())
                    .build()
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

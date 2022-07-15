package com.epam.library.command;

import com.epam.library.command.impl.SearchBookCommand;
import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookService;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class SearchBookCommandTest {

    private final List<Book> books = ImmutableList.of(
            Book.builder()
                    .id(1L)
                    .title("Witcher")
                    .author(Author.builder().id(2L).build())
                    .stock(2)
                    .genre(Genre.builder().id(1L).build())
                    .build()
    );

    @Test
    public void testExecuteShouldSearchBooksByTitleWhenTitleIsNotEmpty() throws ServiceException, PageCommandException {
        // when
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("bookTitle")).thenReturn("Witcher");
        when(request.getParameter("pageNo")).thenReturn("1");

        BookService bookService = mock(BookService.class);
        when(bookService.searchBooksFromPositionByBookTitle(anyString(), anyInt(), anyInt())).thenReturn(books);
        when(bookService.calculateNumberOfRows()).thenReturn(5);
        doNothing().when(request).setAttribute(anyString(), anyInt());
        doNothing().when(request).setAttribute(anyString(), anyList());
        doNothing().when(request).setAttribute(anyString(), anyString());

        SearchBookCommand command = new SearchBookCommand(bookService);

        // then
        CommandResult commandResult = command.execute(request);

        // then
        verify(bookService, times(1)).searchBooksFromPositionByBookTitle(anyString(), anyInt(), anyInt());
        verify(bookService, times(1)).calculateNumberOfRows();
        verify(request, times(3)).setAttribute(anyString(), anyInt());
        verify(request, times(1)).setAttribute(anyString(), anyList());
        verify(request, times(1)).setAttribute(anyString(), anyString());
    }

    @Test
    public void testExecuteShouldSearchBooksByTitleWhenTitleIsEmpty() throws ServiceException, PageCommandException {
        // when
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("bookTitle")).thenReturn("");
        when(request.getParameter("pageNo")).thenReturn("1");

        BookService bookService = mock(BookService.class);
        when(bookService.findPartOfBooks(anyInt(), anyInt())).thenReturn(books);
        when(bookService.calculateNumberOfRows()).thenReturn(5);
        doNothing().when(request).setAttribute(anyString(), anyInt());
        doNothing().when(request).setAttribute(anyString(), anyList());
        doNothing().when(request).setAttribute(anyString(), anyString());

        SearchBookCommand command = new SearchBookCommand(bookService);

        // then
        CommandResult commandResult = command.execute(request);

        // then
        verify(request, times(5)).getParameter(anyString());
        verify(bookService, times(1)).findPartOfBooks(anyInt(), anyInt());
        verify(bookService, times(1)).calculateNumberOfRows();
        verify(request, times(3)).setAttribute(anyString(), anyInt());
        verify(request, times(1)).setAttribute(anyString(), anyList());
        verify(request, times(1)).setAttribute(anyString(), anyString());
    }
}

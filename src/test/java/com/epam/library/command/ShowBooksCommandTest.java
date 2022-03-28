package com.epam.library.command;

import com.epam.library.command.implementation.ShowBooksCommand;
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

public class ShowBooksCommandTest {

    private final List<Book> books = Arrays.asList(
            new Book(1L, "War and Peace", Author.createAuthorWithOnlyIDField(1L), 2, Genre.createGenreWithOnlyIDField(1L)),
            new Book(2L, "Resurrection", Author.createAuthorWithOnlyIDField(1L), 2, Genre.createGenreWithOnlyIDField(1L))
    );

    @Test
    public void testExecuteShouldShowBooksWhenTheyExist() throws ServiceException, PageCommandException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("pageNo")).thenReturn("1");

        BookService bookService = mock(BookService.class);
        when(bookService.findPartOfBooks(anyInt(), anyInt())).thenReturn(books);
        doNothing().when(request).setAttribute(anyString(), anyInt());
        doNothing().when(request).setAttribute(anyString(), anyList());

        ShowBooksCommand command = new ShowBooksCommand(bookService);

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(request, times(4)).getParameter(anyString());
        verify(request, times(3)).setAttribute(anyString(), anyInt());
        verify(request, times(1)).setAttribute(anyString(), anyList());
    }
}

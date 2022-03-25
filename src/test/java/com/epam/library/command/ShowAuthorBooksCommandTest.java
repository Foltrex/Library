package com.epam.library.command;

import com.epam.library.command.implementation.ShowAuthorBooksCommand;
import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookService;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class ShowAuthorBooksCommandTest {

    private final List<Book> authorBooks = Arrays.asList(
            new Book(1L, "War and Piece", Author.createAuthorWithOnlyIDField(1L), 2, Genre.createGenreWithOnlyIDField(1L)),
            new Book(2L, "Anna Karenina", Author.createAuthorWithOnlyIDField(1L),  3, Genre.createGenreWithOnlyIDField(1L))
    );

    @Test
    public void testExecuteShouldReturnBooksBySpecificAuthorWhenThenExist() throws ServiceException, PageCommandException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("authorId")).thenReturn("1");
        when(request.getParameter("authorName")).thenReturn("Leo");
        when(request.getParameter("authorSurname")).thenReturn("Tolstoy");
        when(request.getParameter("pageNo")).thenReturn("1");

        BookService service = mock(BookService.class);
        when(service.searchBooksFromPositionByAuthorId(anyLong(), anyInt(), anyInt())).thenReturn(authorBooks);
        doNothing().when(request).setAttribute(anyString(), anyInt());
        doNothing().when(request).setAttribute(anyString(), anyList());
        doNothing().when(request).setAttribute(anyString(), any(Author.class));

        ShowAuthorBooksCommand command = new ShowAuthorBooksCommand(service);

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(request, times(7)).getParameter(anyString());
        verify(service, times(1)).searchBooksFromPositionByAuthorId(anyLong(), anyInt(), anyInt());
        verify(request, times(3)).setAttribute(anyString(), anyInt());
        verify(request, times(1)).setAttribute(anyString(), anyList());
        verify(request, times(1)).setAttribute(anyString(), any(Author.class));
    }
}

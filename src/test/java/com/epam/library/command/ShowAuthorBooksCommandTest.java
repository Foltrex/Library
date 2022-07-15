package com.epam.library.command;

import com.epam.library.command.impl.ShowAuthorBooksCommand;
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

public class ShowAuthorBooksCommandTest {

    private final List<Book> authorBooks = ImmutableList.of(
            Book.builder()
                    .id(1L)
                    .title("War and Piece")
                    .author(Author.builder().id(1L).build())
                    .stock(2)
                    .genre(Genre.builder().id(1L).build())
                    .build(),
            Book.builder()
                    .id(2L)
                    .title("Anna Karenina")
                    .author(Author.builder().id(1L).build())
                    .stock(3)
                    .genre(Genre.builder().id(1L).build())
                    .build()
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
        verify(service, times(1)).searchBooksFromPositionByAuthorId(anyLong(), anyInt(), anyInt());
        verify(request, times(3)).setAttribute(anyString(), anyInt());
        verify(request, times(1)).setAttribute(anyString(), anyList());
        verify(request, times(1)).setAttribute(anyString(), any(Author.class));
    }
}

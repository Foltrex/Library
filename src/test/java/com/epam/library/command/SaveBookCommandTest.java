package com.epam.library.command;

import com.epam.library.command.impl.SaveBookCommand;
import com.epam.library.entity.Author;
import com.epam.library.entity.Book;
import com.epam.library.entity.Genre;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookService;
import com.epam.library.validator.impl.InputStockValidatorImpl;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class SaveBookCommandTest {

    private final List<Book> books = ImmutableList.of(
            Book.builder()
                    .id(1L)
                    .title("A")
                    .author(Author.builder().id(1L).build())
                    .stock(3)
                    .genre(Genre.builder().id(2L).build())
                    .build(),
            Book.builder()
                    .id(2L)
                    .title("Book Name")
                    .author(Author.builder().id(1L).build())
                    .stock(3)
                    .genre(Genre.builder().id(2L).build())
                    .build()
    );

    @Test
    public void testExecuteShouldSaveBookWhenBookWasEdited() throws ServiceException, PageCommandException {
        // given
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("bookId")).thenReturn("2");
        when(request.getParameter("bookTitle")).thenReturn("Book Name");
        when(request.getParameter("authorId")).thenReturn("1");
        when(request.getParameter("bookStock")).thenReturn("3");
        when(request.getParameter("genreId")).thenReturn("2");

        BookService service = mock(BookService.class);
        doNothing().when(service).saveBook(any(Book.class));

        InputStockValidatorImpl validator = mock(InputStockValidatorImpl.class);
        when(validator.isStockValid(anyInt())).thenReturn(true);

        doNothing().when(request).setAttribute(anyString(), anyList());

        SaveBookCommand command = new SaveBookCommand(service, validator);

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(service, times(1)).saveBook(any(Book.class));
        verify(service, times(1)).findPartOfBooks(anyInt(), anyInt());
        verify(request, times(1)).setAttribute(anyString(), anyList());
    }
}

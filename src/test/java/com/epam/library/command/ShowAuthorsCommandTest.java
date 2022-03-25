package com.epam.library.command;

import com.epam.library.command.implementation.ShowAuthorsCommand;
import com.epam.library.entity.Author;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.AuthorService;
import com.epam.library.service.implementation.AuthorServiceImpl;
import org.junit.Test;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class ShowAuthorsCommandTest {

    private final List<Author> authors = Arrays.asList(
            new Author(1L, "Leo", "Tolstoy"),
            new Author(2L, "Alex", "Pushkin")
    );

    @Test
    public void testExecuteShouldReturnAuthorWhenThenExist() throws ServiceException, PageCommandException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        AuthorService service = mock(AuthorService.class);
        when(service.getAuthors()).thenReturn(authors);
        doNothing().when(request).setAttribute(anyString(), anyList());

        ShowAuthorsCommand command = new ShowAuthorsCommand(service);

        // when
        CommandResult result = command.execute(request);

        // then
        verify(service, times(1)).getAuthors();
        verify(request, times(1)).setAttribute(anyString(), anyList());
    }
}

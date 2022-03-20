package com.epam.library.command;

import com.epam.library.command.implementation.AddAuthorCommand;
import com.epam.library.entity.Author;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.AuthorService;
import com.epam.library.service.implementation.AuthorServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class AddAuthorCommandTest {

    private final List<Author> authors = Arrays.asList(
            new Author(1L, "Alex", "Pushkin"),
            new Author(2L, "Nikolay", "Nosov")
    );

    @Test
    public void testExecuteShouldAddAuthorWhenAuthorIsGiven() throws Exception {
        // given
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getParameter("authorName")).thenReturn("Nikolay");
        when(request.getParameter("authorSurname")).thenReturn("Nosov");

        AuthorService service = mock(AuthorService.class);
        doNothing().when(service).saveAuthor(any(Author.class));
        when(service.getAuthors()).thenReturn(authors);
        doNothing().when(request).setAttribute(anyString(), anyList());

        AddAuthorCommand command = new AddAuthorCommand(service);

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(request, times(2)).getParameter(anyString());
        verify(service, times(1)).saveAuthor(any(Author.class));
        verify(service, times(1)).getAuthors();
        verify(request, times(1)).setAttribute(anyString(), anyList());
    }
}

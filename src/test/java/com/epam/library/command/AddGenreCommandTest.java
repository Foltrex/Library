package com.epam.library.command;

import com.epam.library.command.implementation.AddAuthorCommand;
import com.epam.library.command.implementation.AddGenreCommand;
import com.epam.library.entity.Author;
import com.epam.library.entity.Genre;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.AuthorService;
import com.epam.library.service.GenreService;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AddGenreCommandTest {

    private final List<Genre> genres = Arrays.asList(
            new Genre(1L, "Russian Classic"),
            new Genre(2L, "Horror")
    );

    @Test
    public void testExecuteShouldAddGenreWhenGenreIsGiven() throws ServiceException, PageCommandException {
        // given
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getParameter("genreName")).thenReturn("Horror");

        GenreService service = mock(GenreService.class);
        doNothing().when(service).saveGenre(any(Genre.class));
        when(service.getGenres()).thenReturn(genres);
        doNothing().when(request).setAttribute(anyString(), anyList());

        AddGenreCommand command = new AddGenreCommand(service);

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(service, times(1)).saveGenre(any(Genre.class));
        verify(service, times(1)).getGenres();
        verify(request, times(1)).setAttribute(anyString(), anyList());
    }
}

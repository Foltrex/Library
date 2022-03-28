package com.epam.library.command;

import com.epam.library.command.implementation.ShowGenresCommand;
import com.epam.library.entity.Genre;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.GenreService;
import org.junit.Test;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class ShowGenresCommandTest {

    private final List<Genre> genres = Arrays.asList(
            new Genre(1L, "Russian Classic"),
            new Genre(2L, "English Classic")
    );

    @Test
    public void testExecuteShouldShowGenresWhenTheyExist() throws ServiceException, PageCommandException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        GenreService genreService = mock(GenreService.class);
        when(genreService.getGenres()).thenReturn(genres);
        doNothing().when(request).setAttribute(anyString(), anyList());

        ShowGenresCommand command = new ShowGenresCommand(genreService);

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(request, times(1)).setAttribute(anyString(), anyList());
        verify(genreService, times(1)).getGenres();
    }
}

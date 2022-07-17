package com.epam.library.command;

import com.epam.library.command.impl.AddBookCommand;
import com.epam.library.entity.Author;
import com.epam.library.entity.Genre;
import com.epam.library.service.AuthorService;
import com.epam.library.service.GenreService;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AddBookCommandTest {

    private final List<Author> authors = ImmutableList.of(
            new Author(1L, "Alex", "Pushkin"),
            new Author(2L, "Nikolay", "Nosov")
    );

    private final List<Genre> genres = ImmutableList.of(
            new Genre(1L, "Russian Classic"),
            new Genre(2L, "Horror")
    );

    @Test
    public void testExecuteShouldAddBookWhenBookIsGiven() throws Exception {
        // given
        AuthorService authorService = mock(AuthorService.class);
        when(authorService.getAuthors()).thenReturn(authors);

        GenreService genreService = mock(GenreService.class);
        when(genreService.getGenres()).thenReturn(genres);

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        doNothing().when(request).setAttribute(anyString(), anyList());

        AddBookCommand command = new AddBookCommand(authorService, genreService);

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(authorService, times(1)).getAuthors();
        verify(genreService, times(1)).getGenres();
        verify(request, times(2)).setAttribute(anyString(), anyList());
    }
}

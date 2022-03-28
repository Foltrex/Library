package com.epam.library.command;

import com.epam.library.command.implementation.ShowBookRentalsCommand;
import com.epam.library.entity.Book;
import com.epam.library.entity.BookRental;
import com.epam.library.entity.RentalStatus;
import com.epam.library.entity.User;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookRentalService;
import org.junit.Test;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ShowBookRentalsCommandTest {

    private final List<BookRental> rentals = Arrays.asList(
            new BookRental(1L, User.createUserWithOnlyId(1L), Book.createBookWithOnlyId(1L), new Date(), new Date(), RentalStatus.RETURNED),
            new BookRental(2L, User.createUserWithOnlyId(2L), Book.createBookWithOnlyId(2L), new Date(), new Date(), RentalStatus.RETURNED)
    );

    @Test
    public void testExecuteShouldShowBookRentalsWhenTheyExist() throws ServiceException, PageCommandException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        BookRentalService service = mock(BookRentalService.class);
        when(service.getBookRentals()).thenReturn(rentals);
        doNothing().when(request).setAttribute(anyString(), anyList());

        ShowBookRentalsCommand command = new ShowBookRentalsCommand(service);

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(request, times(1)).setAttribute(anyString(), anyList());
        verify(service, times(1)).getBookRentals();
    }
}

package com.epam.library.command;

import com.epam.library.command.implementation.BorrowBookCommand;
import com.epam.library.entity.*;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookRentalService;
import com.epam.library.service.implementation.BookRentalServiceImpl;
import org.junit.Test;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class BorrowBookCommandTest {

    private final List<BookRental> rentals = Arrays.asList(
            new BookRental(1L, User.createUserWithOnlyId(1L), Book.createBookWithOnlyId(1L), null, null, RentalStatus.RETURNED),
            new BookRental(2L, User.createUserWithOnlyId(2L), Book.createBookWithOnlyId(2L), null, null, RentalStatus.ISSUED_FOR_SUBSCRIPTION)
    );


    @Test
    public void testExecuteShouldBorrowBookWhenNecessaryDataIdGiven() throws ServiceException, PageCommandException {
        // given
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("bookId")).thenReturn("2");
        when(request.getParameter("userId")).thenReturn("2");

        BookRentalService service = mock(BookRentalService.class);
        doNothing().when(service).saveBookRental(any(BookRental.class));
        when(service.getBookRentals()).thenReturn(rentals);
        doNothing().when(request).setAttribute(anyString(), anyList());

        BorrowBookCommand command = new BorrowBookCommand(service);

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(request, times(2)).getParameter(anyString());
        verify(service, times(1)).saveBookRental(any(BookRental.class));
        verify(service, times(1)).getBookRentals();
        verify(request, times(1)).setAttribute(anyString(), anyList());
    }
}

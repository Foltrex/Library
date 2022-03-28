package com.epam.library.command;

import com.epam.library.command.implementation.SaveBookRentalCommand;
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

public class SaveBookRentalCommandTest {

    private final List<BookRental> rentals = Arrays.asList(
            new BookRental(1L, User.createUserWithOnlyId(1L), Book.createBookWithOnlyId(1L), new Date(), new Date(), RentalStatus.RETURNED),
            new BookRental(2L, User.createUserWithOnlyId(2L), Book.createBookWithOnlyId(2L), new Date(), new Date(), RentalStatus.ISSUED_FOR_SUBSCRIPTION)
    );

    @Test
    public void testExecuteShouldSaveBookRentalWhenBookRentalWasEdited() throws ServiceException, PageCommandException {
        // given
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("bookRentalId")).thenReturn("2");
        when(request.getParameter("userId")).thenReturn("2");
        when(request.getParameter("bookId")).thenReturn("2");
        when(request.getParameter("borrowDate")).thenReturn("2022-01-01");
        when(request.getParameter("returnDate")).thenReturn("2022-02-01");
        when(request.getParameter("rentalStatus")).thenReturn("issued for subscription");

        BookRentalService bookRentalService = mock(BookRentalService.class);
        doNothing().when(bookRentalService).saveBookRental(any(BookRental.class));
        when(bookRentalService.getBookRentals()).thenReturn(rentals);

        doNothing().when(request).setAttribute(anyString(), anyList());

        SaveBookRentalCommand command = new SaveBookRentalCommand(bookRentalService);

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(request, times(6)).getParameter(anyString());
        verify(bookRentalService, times(1)).saveBookRental(any(BookRental.class));
        verify(bookRentalService, times(1)).getBookRentals();
        verify(request, times(1)).setAttribute(anyString(), anyList());
    }
}
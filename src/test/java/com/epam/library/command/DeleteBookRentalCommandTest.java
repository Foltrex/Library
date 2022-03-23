package com.epam.library.command;

import com.epam.library.command.implementation.DeleteBookRentalCommand;
import com.epam.library.entity.Book;
import com.epam.library.entity.BookRental;
import com.epam.library.entity.RentalStatus;
import com.epam.library.entity.User;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookRentalService;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.junit.Test;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class DeleteBookRentalCommandTest {

    private final List<BookRental> rentals = Arrays.asList(
            new BookRental(1L, User.createUserWithOnlyId(1L), Book.createBookWithOnlyId(1L), null, null, RentalStatus.RETURNED),
            new BookRental(2L, User.createUserWithOnlyId(2L), Book.createBookWithOnlyId(2L), null, null, RentalStatus.ISSUED_FOR_SUBSCRIPTION)
    );

    @Test
    public void testExecuteShouldDeleteBookRentalWhenIdGiven() throws ServiceException, PageCommandException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("bookId")).thenReturn("1");
        when(request.getParameter("bookRentalId")).thenReturn("1");
        when(request.getParameter("rentalStatus")).thenReturn("returned");

        BookRentalService service = mock(BookRentalService.class);
        doNothing().when(service).deleteBookRental(any(BookRental.class));
        when(service.getBookRentals()).thenReturn(rentals);
        doNothing().when(request).setAttribute(anyString(), anyList());

        DeleteBookRentalCommand command = new DeleteBookRentalCommand(service);

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(request, times(3)).getParameter(anyString());
        verify(service, times(1)).deleteBookRental(any(BookRental.class));
        verify(service, times(1)).getBookRentals();
        verify(request, times(1)).setAttribute(anyString(), anyList());
    }
}

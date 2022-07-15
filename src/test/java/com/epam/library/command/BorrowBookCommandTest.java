package com.epam.library.command;

import com.epam.library.command.impl.BorrowBookCommand;
import com.epam.library.entity.*;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookRentalService;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class BorrowBookCommandTest {

    private final List<BookRental> rentals = ImmutableList.of(
            BookRental.builder()
                    .id(1L)
                    .user(User.builder().id(1L).build())
                    .rentedBook(Book.builder().id(1L).build())
                    .rentalStatus(RentalStatus.RETURNED)
                    .build(),

            BookRental.builder()
                    .id(2L)
                    .user(User.builder().id(2L).build())
                    .rentedBook(Book.builder().id(2L).build())
                    .rentalStatus(RentalStatus.ISSUED_FOR_SUBSCRIPTION)
                    .build()
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
        verify(service, times(1)).saveBookRental(any(BookRental.class));
        verify(service, times(1)).getBookRentals();
        verify(request, times(1)).setAttribute(anyString(), anyList());
    }
}

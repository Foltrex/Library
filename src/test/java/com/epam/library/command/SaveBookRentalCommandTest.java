package com.epam.library.command;

import com.epam.library.command.impl.SaveBookRentalCommand;
import com.epam.library.entity.Book;
import com.epam.library.entity.BookRental;
import com.epam.library.entity.RentalStatus;
import com.epam.library.entity.User;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookRentalService;
import com.epam.library.validator.impl.InputDateValidatorImpl;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SaveBookRentalCommandTest {

    private final List<BookRental> rentals = ImmutableList.of(
            BookRental.builder()
                    .id(1L)
                    .user(User.builder().id(1L).build())
                    .rentedBook(Book.builder().id(1L).build())
                    .borrowDate(new Date())
                    .returnDate(new Date())
                    .rentalStatus(RentalStatus.RETURNED)
                    .build(),

            BookRental.builder()
                    .id(2L)
                    .user(User.builder().id(2L).build())
                    .rentedBook(Book.builder().id(2L).build())
                    .borrowDate(new Date())
                    .returnDate(new Date())
                    .rentalStatus(RentalStatus.ISSUED_FOR_SUBSCRIPTION)
                    .build()
    );

    @Test
    public void testExecuteShouldSaveBookRentalWhenBookRentalWasEdited() throws ServiceException, PageCommandException {
        // given
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("bookRentalId")).thenReturn("2");
        when(request.getParameter("userId")).thenReturn("2");
        when(request.getParameter("bookId")).thenReturn("2");
        when(request.getParameter("bookRentalBorrowDate")).thenReturn("2022-01-01");
        when(request.getParameter("bookRentalReturnDate")).thenReturn("2022-02-01");
        when(request.getParameter("bookRentalRentalStatus")).thenReturn("issued for subscription");

        BookRentalService bookRentalService = mock(BookRentalService.class);
        doNothing().when(bookRentalService).saveBookRental(any(BookRental.class));
        when(bookRentalService.getBookRentals()).thenReturn(rentals);

        InputDateValidatorImpl validator = mock(InputDateValidatorImpl.class);
        when(validator.isDatesValid(any(Date.class), any(Date.class))).thenReturn(true);

        doNothing().when(request).setAttribute(anyString(), anyList());

        SaveBookRentalCommand command = new SaveBookRentalCommand(bookRentalService, validator);

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(bookRentalService, times(1)).saveBookRental(any(BookRental.class));
        verify(bookRentalService, times(1)).getBookRentals();
        verify(request, times(1)).setAttribute(anyString(), anyList());
    }
}

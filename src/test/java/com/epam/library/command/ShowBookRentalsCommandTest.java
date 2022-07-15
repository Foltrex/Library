package com.epam.library.command;

import com.epam.library.command.impl.ShowBookRentalsCommand;
import com.epam.library.entity.*;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.BookRentalService;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ShowBookRentalsCommandTest {

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
                    .rentalStatus(RentalStatus.RETURNED)
                    .build()
    );

    @Test
    public void testExecuteShouldShowBookRentalsWhenTheyExist() throws ServiceException, PageCommandException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        BookRentalService service = mock(BookRentalService.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("userRole")).thenReturn(Role.ADMIN);
        when(service.getBookRentals()).thenReturn(rentals);
        doNothing().when(request).setAttribute(anyString(), anyList());

        ShowBookRentalsCommand command = new ShowBookRentalsCommand(service);

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(request, times(1)).setAttribute(anyString(), anyList());
        verify(session, times(1)).getAttribute(anyString());
        verify(service, times(1)).getBookRentals();
    }
}

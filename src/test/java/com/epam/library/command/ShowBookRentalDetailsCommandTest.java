package com.epam.library.command;

import com.epam.library.command.implementation.ShowBookRentalDetailsCommand;
import com.epam.library.dao.BookDaoImplTest;
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
import java.util.Date;
import java.util.Optional;

public class ShowBookRentalDetailsCommandTest {

    private final Optional<BookRental> optionalBookRental = Optional.of(
            new BookRental(1L, User.createUserWithOnlyId(1L), Book.createBookWithOnlyId(1L), new Date(), new Date(), RentalStatus.WAITING_FOR_ISSUANCE)
    );

    @Test
    public void testExecuteShouldShowBookRentalDetailsWhenRentalIdIsGiven() throws ServiceException, PageCommandException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("bookRentalId")).thenReturn("1");
        doNothing().when(request).setAttribute(anyString(), any());

        BookRentalService service = mock(BookRentalService.class);
        when(service.getBookRental(anyLong())).thenReturn(optionalBookRental);

        ShowBookRentalDetailsCommand command = new ShowBookRentalDetailsCommand(service);

        // when
        CommandResult commandResult = command.execute(request);

        // then
        verify(request, times(2)).setAttribute(anyString(), any());
        verify(service, times(1)).getBookRental(anyLong());
    }
}

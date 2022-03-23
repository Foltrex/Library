package com.epam.library.command;

import com.epam.library.exception.ServiceException;
import com.epam.library.service.EntityService;
import org.junit.Test;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

public class PaginatorTest {

    @Test
    public void testSetPaginationParametersShouldSetParameterWhenIsGiven() throws ServiceException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("pageNo")).thenReturn("1");

        EntityService service = mock(EntityService.class);
        when(service.calculateNumberOfRows()).thenReturn(6);

        doNothing().when(request).setAttribute(anyString(), anyInt());

        Paginator paginator = new Paginator(service, 4);

        // when
        paginator.setPaginationParameters(request);

        // then
        verify(request, times(2)).getParameter(anyString());
        verify(service, times(1)).calculateNumberOfRows();
        verify(request, times(3)).setAttribute(anyString(), anyInt());
    }
}

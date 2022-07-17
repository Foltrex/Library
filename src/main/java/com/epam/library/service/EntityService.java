package com.epam.library.service;

import com.epam.library.exception.ServiceException;

public interface EntityService {
    int calculateNumberOfRows() throws ServiceException;
}

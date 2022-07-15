package com.epam.library.command;

import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

/** Command which extracts from request parameters and perform some actions with data(view, delete, update, add) */
public interface Command {
    CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException;
}

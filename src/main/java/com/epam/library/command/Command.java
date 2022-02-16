package com.epam.library.command;

import com.epam.library.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest req) throws ServiceException;
}

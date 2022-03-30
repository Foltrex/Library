package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class ShowPageCommand implements Command {

    private final String page;

    public ShowPageCommand(String page) {
        this.page = page;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        return CommandResult.redirect(req.getContextPath() + page);
    }
}

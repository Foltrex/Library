package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandName;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {
    private static final String LANGUAGE_PARAMETER = "lang";
    private static final String LANGUAGE_ATTRIBUTE = "locale";

    private static final String MAIN_PAGE_COMMAND = CommandName.SHOW_BOOKS.getServletCommand("controller");

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        String lang = req.getParameter(LANGUAGE_PARAMETER);
        HttpSession session = req.getSession();
        session.setAttribute(LANGUAGE_ATTRIBUTE, lang);

        return CommandResult.forward(MAIN_PAGE_COMMAND);
    }
}

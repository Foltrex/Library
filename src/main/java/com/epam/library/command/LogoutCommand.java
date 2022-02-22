package com.epam.library.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    private static final String LOGIN_PAGE = "/index.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.invalidate();
        return CommandResult.forward(LOGIN_PAGE);
    }
}

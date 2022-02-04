package com.epam.library.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String page = "index.jsp";
        HttpSession session = req.getSession();
        session.invalidate();

        return page;
    }
}

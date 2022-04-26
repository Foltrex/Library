package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.invalidate();
        return CommandResult.redirect(Page.LOGIN.getPath());
    }
}

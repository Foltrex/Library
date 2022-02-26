package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;

import javax.servlet.http.HttpServletRequest;

public class BookDetailsCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req) {
        return null;
    }
}

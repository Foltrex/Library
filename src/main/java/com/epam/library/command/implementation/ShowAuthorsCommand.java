package com.epam.library.command.implementation;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.entity.Author;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.service.AuthorService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAuthorsCommand implements Command {

    // TODO: make pagination
    private final AuthorService authorService;

    public ShowAuthorsCommand(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        List<Author> authors = authorService.getAuthors();
        req.setAttribute("authors", authors);
        return CommandResult.forward(Page.AUTHORS.getPath());
    }
}

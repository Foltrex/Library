package com.epam.library.command.impl;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.command.Page;
import com.epam.library.entity.Author;
import com.epam.library.exception.PageCommandException;
import com.epam.library.exception.ServiceException;
import com.epam.library.extractor.RequestExtractor;
import com.epam.library.extractor.impl.AuthorRequestExtractor;
import com.epam.library.service.AuthorService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/** Saves new author in DB */
public class AddAuthorCommand implements Command {

    private final RequestExtractor<Author> requestExtractor = new AuthorRequestExtractor();

    private final AuthorService authorService;

    public AddAuthorCommand(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req) throws ServiceException, PageCommandException {
        Author author = requestExtractor.extract(req);
        authorService.saveAuthor(author);

        List<Author> authors = authorService.getAuthors();
        req.setAttribute("authors", authors);
        return CommandResult.forward(Page.AUTHORS.getPath());
    }
}

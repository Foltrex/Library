package com.epam.library.extractor.implementation;

import com.epam.library.entity.Author;
import com.epam.library.extractor.AbstractRequestExtractor;
import com.epam.library.extractor.parameter.name.AuthorRequestParameterName;

import javax.servlet.http.HttpServletRequest;

public class AuthorRequestExtractor extends AbstractRequestExtractor<Author> {

    @Override
    public Author extract(HttpServletRequest request) {
        String authorIdString = request.getParameter(AuthorRequestParameterName.ID.getName());

        Long authorId = super.isParsable(authorIdString) ? Long.valueOf(authorIdString) : null;
        String authorName = request.getParameter(AuthorRequestParameterName.NAME.getName());
        String authorSurname = request.getParameter(AuthorRequestParameterName.SURNAME.getName());

        return new Author(authorId, authorName, authorSurname);
    }
}

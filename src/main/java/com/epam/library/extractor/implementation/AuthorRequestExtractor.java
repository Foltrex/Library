package com.epam.library.extractor.implementation;

import com.epam.library.entity.Author;
import com.epam.library.extractor.RequestExtractor;
import com.epam.library.extractor.parameter.name.AuthorRequestParameterName;

import javax.servlet.http.HttpServletRequest;

public class AuthorRequestExtractor implements RequestExtractor<Author> {

    @Override
    public Author extract(HttpServletRequest request) {
        String authorIdString = request.getParameter(AuthorRequestParameterName.ID.getName());

        Long authorId = (authorIdString != null) ? Long.valueOf(authorIdString) : null;
        String authorName = request.getParameter(AuthorRequestParameterName.NAME.getName());
        String authorSurname = request.getParameter(AuthorRequestParameterName.SURNAME.getName());

        return new Author(authorId, authorName, authorSurname);
    }
}

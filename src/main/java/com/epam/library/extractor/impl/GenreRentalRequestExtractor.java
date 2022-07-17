package com.epam.library.extractor.impl;

import com.epam.library.entity.Genre;
import com.epam.library.extractor.AbstractRequestExtractor;
import com.epam.library.extractor.parameter.name.GenreRequestParameterName;

import javax.servlet.http.HttpServletRequest;

/** Extract {@link com.epam.library.entity.Genre} object from request */
public class GenreRentalRequestExtractor extends AbstractRequestExtractor<Genre> {
    @Override
    public Genre extract(HttpServletRequest request) {
        String genreIdString = request.getParameter(GenreRequestParameterName.ID.getName());

        Long genreId = super.isParsable(genreIdString) ? Long.valueOf(genreIdString) : null;
        String genreName = request.getParameter(GenreRequestParameterName.NAME.getName());

        return new Genre(genreId, genreName);
    }
}

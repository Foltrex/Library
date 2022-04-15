package com.epam.library.extractor.implementation;

import com.epam.library.entity.Genre;
import com.epam.library.extractor.RequestExtractor;
import com.epam.library.extractor.parameter.name.GenreRequestParameterName;

import javax.servlet.http.HttpServletRequest;

public class GenreRentalRequestExtractor implements RequestExtractor<Genre> {
    @Override
    public Genre extract(HttpServletRequest request) {
        String genreIdString = request.getParameter(GenreRequestParameterName.ID.getName());

        Long genreId = (genreIdString != null) ? Long.valueOf(genreIdString) : null;
        String genreName = request.getParameter(GenreRequestParameterName.NAME.getName());

        return new Genre(genreId, genreName);
    }
}

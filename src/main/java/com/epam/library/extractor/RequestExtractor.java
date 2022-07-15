package com.epam.library.extractor;

import com.epam.library.entity.Identifable;

import javax.servlet.http.HttpServletRequest;

/**
 * Extracts entity from request parameters
 *
 * @param <T>  the entity extracted from request parameters
 */
public interface RequestExtractor<T extends Identifable> {

    T extract(HttpServletRequest request);
}

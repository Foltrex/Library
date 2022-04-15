package com.epam.library.extractor;

import com.epam.library.entity.Identifable;

import javax.servlet.http.HttpServletRequest;

public interface RequestExtractor<T extends Identifable> {

    T extract(HttpServletRequest request);
}

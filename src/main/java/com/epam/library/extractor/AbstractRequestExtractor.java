package com.epam.library.extractor;

import com.epam.library.entity.Identifable;

public abstract class AbstractRequestExtractor<T extends Identifable> implements RequestExtractor<T> {

    protected boolean isParsable(String requestParameter) {
        return requestParameter != null && !requestParameter.isEmpty();
    }
}

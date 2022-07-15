package com.epam.library.validator.impl;

import com.epam.library.validator.InputStockValidator;

/** Validates book stock in library */
public class InputStockValidatorImpl implements InputStockValidator {
    @Override
    public boolean isStockValid(int stock) {
        return stock > 0;
    }
}

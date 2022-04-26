package com.epam.library.validator.impl;

import com.epam.library.validator.InputStockValidator;

public class InputStockValidatorImpl implements InputStockValidator {
    @Override
    public boolean isStockValid(int stock) {
        return stock > 0;
    }
}

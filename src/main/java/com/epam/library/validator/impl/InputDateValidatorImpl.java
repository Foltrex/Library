package com.epam.library.validator.impl;

import com.epam.library.validator.InputDateValidator;

import java.util.Date;

/** Validates dates */
public class InputDateValidatorImpl implements InputDateValidator {

    @Override
    public boolean isDatesValid(Date borrowDate, Date returnDate) {
        return returnDate.getTime() - borrowDate.getTime() > 0L;
    }
}

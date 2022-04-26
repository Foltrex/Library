package com.epam.library.validator;

import java.util.Date;

public interface InputDateValidator {

    boolean isDatesValid(Date borrowDate, Date returnDate);
}

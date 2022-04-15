package com.epam.library.extractor.parameter.name;

public enum BookRentalRequestParameterName {
    ID("bookRentalId"),
    BORROW_DATE("bookRentalBorrowDate"),
    RETURN_DATE("bookRentalReturnDate"),
    RENTAL_STATUS("bookRentalRentalStatus");

    private final String name;

    BookRentalRequestParameterName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

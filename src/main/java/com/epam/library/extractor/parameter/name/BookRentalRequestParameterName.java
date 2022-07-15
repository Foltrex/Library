package com.epam.library.extractor.parameter.name;


/** Request parameters associated with the {@link com.epam.library.entity.BookRental} */
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

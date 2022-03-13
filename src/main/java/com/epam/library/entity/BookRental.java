package com.epam.library.entity;

import java.util.Date;
import java.util.Objects;

public class BookRental extends Entity {

    public static final String TABLE = "borrows";

    public static final String USER_ID = "user_id";
    public static final String USER_LOGIN = "user_login";
    public static final String BOOK_ID = "book_id";
    public static final String BOOK_TITLE = "book_title";
    public static final String BORROW_DATE = "borrow_date";
    public static final String RETURN_DATE = "return_date";
    public static final String STATUS = "status";

    private User user;
    private Book rentedBook;
    private Date borrowDate;
    private Date returnDate;
    private RentalStatus rentalStatus;

    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(RentalStatus rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public BookRental(Long id, User user, Book rentedBook, Date borrowDate, Date returnDate, RentalStatus rentalStatus) {
        super(id);
        this.user = user;
        this.rentedBook = rentedBook;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.rentalStatus = rentalStatus;
    }

    public static BookRental borrow(User user, Book book) {
        return new BookRental(null, user, book, null, null, RentalStatus.WAITING_FOR_ISSUANCE);
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getRentedBook() {
        return rentedBook;
    }

    public void setRentedBook(Book rentedBook) {
        this.rentedBook = rentedBook;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookRental)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        BookRental that = (BookRental) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(rentedBook, that.rentedBook) &&
                Objects.equals(borrowDate, that.borrowDate) &&
                Objects.equals(returnDate, that.returnDate) &&
                rentalStatus == that.rentalStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, rentedBook, borrowDate, returnDate, rentalStatus);
    }

    @Override
    public String toString() {
        return "BookRental{" +
                "user=" + user +
                ", rentedBook=" + rentedBook +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", rentalStatus=" + rentalStatus +
                '}';
    }
}

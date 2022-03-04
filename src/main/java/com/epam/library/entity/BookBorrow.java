package com.epam.library.entity;

import java.util.Date;

public class BookBorrow extends Entity {

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
    private BorrowStatus borrowStatus;

    public BookBorrow(Long id, User user, Book rentedBook, Date borrowDate, Date returnDate, BorrowStatus borrowStatus) {
        super(id);
        this.user = user;
        this.rentedBook = rentedBook;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.borrowStatus = borrowStatus;
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

    public BorrowStatus getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(BorrowStatus borrowStatus) {
        this.borrowStatus = borrowStatus;
    }
}

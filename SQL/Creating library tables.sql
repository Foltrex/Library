CREATE DATABASE library;
USE library;

CREATE TABLE genres (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(70) NOT NULL
);

CREATE TABLE authors (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL
);

CREATE TABLE books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    author_id INT NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    genre_id INT NOT NULL,
    FOREIGN KEY (genre_id) REFERENCES genres (id),
    FOREIGN KEY (author_id) REFERENCES authors (id)
);

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    login VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(32) NOT NULL,
    role enum ('admin', 'librarian', 'reader') NOT NULL
);

CREATE TABLE borrows (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    borrow_date DATE,
    return_date DATE,
    status enum ('waiting for issuance', 'issued to the reading room', 'issued for subscription', 'returned') NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

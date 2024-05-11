package com.demo.maids.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class BookAlreadyBorrowedException extends RuntimeException{
    public BookAlreadyBorrowedException(String message) {
        super(message);
    }
}

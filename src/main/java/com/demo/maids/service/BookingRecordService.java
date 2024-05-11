package com.demo.maids.service;

import com.demo.maids.model.Book;
import com.demo.maids.model.Patron;

public interface BookingRecordService {
    void borrowBook(Book book, Patron patron);
    void returnBook(Book book, Patron patron);
}

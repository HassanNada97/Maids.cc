package com.demo.maids.service;

import com.demo.maids.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findBookById(int id);
    List<Book> findAllBooks();
    void deleteBook(int id);
    Book createBook(Book book);
    Book updateBook(Book book, int id);

}

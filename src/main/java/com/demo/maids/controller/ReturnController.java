package com.demo.maids.controller;

import com.demo.maids.exception.RecordNotFoundException;
import com.demo.maids.model.Book;
import com.demo.maids.model.Patron;
import com.demo.maids.service.BookService;
import com.demo.maids.service.BookingRecordService;
import com.demo.maids.service.PatronService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/return")
public class ReturnController {
    private final BookingRecordService bookingRecordService;
    private final BookService bookService;
    private final PatronService patronService;

    public ReturnController(BookingRecordService bookingRecordService, BookService bookService, PatronService patronService) {
        this.bookingRecordService = bookingRecordService;
        this.bookService = bookService;
        this.patronService = patronService;
    }

    @PostMapping("/{bookId}/patron/{patronId}")
    public void retrunBook(@PathVariable int bookId, @PathVariable int patronId){
        Optional<Book> optionalBook = this.bookService.findBookById(bookId);
        if(optionalBook.isEmpty())
            throw new RecordNotFoundException("Book with Id: "+bookId+" does not exist");
        Optional<Patron> optionalPatron = this.patronService.findPatronById(patronId);
        if(optionalPatron.isEmpty())
            throw new RecordNotFoundException("Patron with Id: "+patronId+" does not exist");
        Book book = optionalBook.get();
        Patron patron = optionalPatron.get();
        this.bookingRecordService.returnBook(book, patron);

    }
}

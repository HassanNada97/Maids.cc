package com.demo.maids.controller;

import com.demo.maids.service.BookService;
import com.demo.maids.exception.RecordNotFoundException;
import com.demo.maids.model.Book;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAllBooks(){
        return this.bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBook(@PathVariable int id ){
        Optional<Book> optionalBook = this.bookService.findBookById(id);
        if(optionalBook.isEmpty())
            throw new RecordNotFoundException("Book with Id: "+id+" does not exist");
        Book book = optionalBook.get();
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id){
        this.bookService.deleteBook(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody @Valid Book book, @PathVariable int id){
       return this.bookService.updateBook(book, id);
    }

    @PostMapping
    public Book createBook(@RequestBody @Valid Book book){
        return this.bookService.createBook(book);
    }

}

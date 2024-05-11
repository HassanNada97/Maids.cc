package com.demo.maids.service.implementation;

import com.demo.maids.service.BookService;
import com.demo.maids.exception.RecordNotFoundException;
import com.demo.maids.model.Book;
import com.demo.maids.repository.BookRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<Book> findBookById(int id) {
        return this.bookRepository.findById(id);
    }

    @Override
    @Cacheable("books")
    public List<Book> findAllBooks() {
        System.err.println("accessing method find all books in service");
        return this.bookRepository.findAll();
    }

    @Override
    @CacheEvict(value = "books", allEntries = true)
    public void deleteBook(int id) {
        if(!this.bookRepository.existsById(id))
            throw new RecordNotFoundException("Book with Id: "+id+" does not exist");
        this.bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    @CacheEvict(value = "books", allEntries = true)
    public Book createBook(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    @Transactional
    @CacheEvict(value = "books", allEntries = true)
    public Book updateBook(Book book, int id) {
        if(!this.bookRepository.existsById(id))
            throw new RecordNotFoundException("Book with Id: "+id+" does not exist");
        book.setId(id);
        this.bookRepository.save(book);
        return book;
    }
}

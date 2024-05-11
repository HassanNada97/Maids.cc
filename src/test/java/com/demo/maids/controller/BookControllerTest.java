package com.demo.maids.controller;

import com.demo.maids.exception.RecordNotFoundException;
import com.demo.maids.model.Book;
import com.demo.maids.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BookControllerTest {
    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;



    @Test
    public void testFindAllBooks() {
        Book book1 = new Book();
        book1.setAuthor("Mark");
        book1.setIsbn("xxxx-yyyy");
        book1.setPublicationYear((short) 2020);
        book1.setTitle("Title1");
        Book book2 = new Book();
        book2.setAuthor("John");
        book2.setIsbn("xxxx-yyyy");
        book2.setPublicationYear((short) 2020);
        book2.setTitle("Title2");
        
        List<Book> expectedBooks = Arrays.asList(book1, book2);
        Mockito.when(bookService.findAllBooks()).thenReturn(expectedBooks);

        List<Book> actualBooks = bookController.findAllBooks();
        System.err.println("actual books "+actualBooks);

        assertEquals(expectedBooks, actualBooks);
    }


    @Test
    public void testFindBook_Success() {
        int bookId = 1;
        Book expectedBook = new Book();
        expectedBook.setId(bookId);
        expectedBook.setTitle("Book 1");
        Mockito.when(bookService.findBookById(bookId)).thenReturn(Optional.of(expectedBook));

        ResponseEntity<Book> responseEntity = bookController.findBook(bookId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedBook, responseEntity.getBody());
        System.err.println("response entity: "+responseEntity.getBody());
    }

    @Test
    public void testFindBook_NotFound() {
        int bookId = 1;
        Mockito.when(bookService.findBookById(bookId)).thenReturn(Optional.empty());

        try {
            bookController.findBook(bookId);
            fail("Expected RecordNotFoundException");
        } catch (RecordNotFoundException e) {
            assertEquals("Book with Id: "+bookId+" does not exist", e.getMessage());
        }
    }

    @Test
    public void testDeleteBook() {
        int bookId = 1;

        // Mock successful deletion by bookService
        Mockito.doNothing().when(bookService).deleteBook(bookId);

        bookController.deleteBook(bookId);


        Mockito.verify(bookService, times(1)).deleteBook(bookId);
    }


    @Test
    public void testUpdateBook() {
        int bookId = 1;
        Book updatedBook = new Book();
        updatedBook.setId(bookId);
        updatedBook.setTitle("Updated Title");

        // Mock successful update by bookService
        Mockito.when(bookService.updateBook(updatedBook, bookId)).thenReturn(updatedBook);

        Book returnedBook = bookController.updateBook(updatedBook, bookId);

        assertEquals(updatedBook, returnedBook);
    }



    @Test
    public void testCreateBook_Success() {
        Book newBook = new Book();
        newBook.setTitle("New Book");

        // Mock successful creation by bookService
        Mockito.when(bookService.createBook(newBook)).thenReturn(newBook);

        Book createdBook = bookController.createBook(newBook);

        assertEquals(newBook, createdBook);
    }
}
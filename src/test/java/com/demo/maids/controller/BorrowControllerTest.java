package com.demo.maids.controller;

import com.demo.maids.exception.RecordNotFoundException;
import com.demo.maids.model.Book;
import com.demo.maids.model.Patron;
import com.demo.maids.service.BookService;
import com.demo.maids.service.BookingRecordService;
import com.demo.maids.service.PatronService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BorrowControllerTest {

    @Mock
    private BookingRecordService bookingRecordService;
    @Mock
    private BookService bookService;
    @Mock
    private PatronService patronService;

    @InjectMocks
    private BorrowController borrowController;

    @Test
    public void testBorrowBookSuccess() throws Exception {
        int bookId = 1;
        int patronId = 2;
        Book expectedBook = new Book();
        expectedBook.setId(bookId);
        expectedBook.setTitle("Test Book");
        Patron expectedPatron = new Patron();
        expectedPatron.setId(patronId);
        expectedPatron.setName("Test Patron");

        // Mock successful lookups
        Mockito.when(bookService.findBookById(bookId)).thenReturn(Optional.of(expectedBook));
        Mockito.when(patronService.findPatronById(patronId)).thenReturn(Optional.of(expectedPatron));



        borrowController.borrowBook(bookId, patronId);

        // Verify interactions with mocked services (optional)
        Mockito.verify(bookService, times(1)).findBookById(bookId);
        Mockito.verify(patronService, times(1)).findPatronById(patronId);
        Mockito.verify(bookingRecordService, times(1)).borrowBook(expectedBook, expectedPatron);
    }

    @Test
    public void testReturnBookBookNotFound() throws Exception {
        int bookId = 1;
        int patronId = 2;

        // Mock book not found
        Mockito.when(bookService.findBookById(bookId)).thenReturn(Optional.empty());

        // No need to mock other services as exception happens before

        try {
            borrowController.borrowBook(bookId, patronId);
            fail("Expected RecordNotFoundException");
        } catch (RecordNotFoundException e) {
            assertEquals("Book with Id: "+bookId+" does not exist", e.getMessage());
        }
    }

    @Test
    public void testReturnBookPatronNotFound() throws Exception {
        int bookId = 1;
        int patronId = 2;
        Book expectedBook = new Book();
        expectedBook.setId(bookId);
        expectedBook.setTitle("Test Book");

        // Mock book found
        Mockito.when(bookService.findBookById(bookId)).thenReturn(Optional.of(expectedBook));

        // Mock patron not found
        Mockito.when(patronService.findPatronById(patronId)).thenReturn(Optional.empty());





        try {
            borrowController.borrowBook(bookId, patronId);
            fail("Expected RecordNotFoundException");
        } catch (RecordNotFoundException e) {
            assertEquals("Patron with Id: "+patronId+" does not exist", e.getMessage());
        }


    }
}
package com.demo.maids.service.implementation;

import com.demo.maids.exception.BookAlreadyBorrowedException;
import com.demo.maids.exception.RecordNotFoundException;
import com.demo.maids.model.Book;
import com.demo.maids.model.BookingRecord;
import com.demo.maids.model.Patron;
import com.demo.maids.repository.BookingRecordRepository;
import com.demo.maids.service.BookingRecordService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookingRecordServiceImpl implements BookingRecordService {

    private final BookingRecordRepository bookingRecordRepository;

    public BookingRecordServiceImpl(BookingRecordRepository bookingRecordRepository) {
        this.bookingRecordRepository = bookingRecordRepository;
    }

    @Override
    public void borrowBook(Book book, Patron patron) {
        boolean alreadyBorrowedBook = this.bookingRecordRepository.userAlreadyBorrowedBook(book.getId(), patron.getId());
        if (alreadyBorrowedBook)
            throw new BookAlreadyBorrowedException("Patron "+patron.getName()+" already borrowed "+book.getTitle());
        BookingRecord newBookingRecord = new BookingRecord();
        newBookingRecord.setBook(book);
        newBookingRecord.setPatron(patron);
        newBookingRecord.setBorrowingDate(new Date());
        this.bookingRecordRepository.save(newBookingRecord);
    }

    @Override
    public void returnBook(Book book, Patron patron) {
        BookingRecord bookingRecord = this.bookingRecordRepository.findBookingRecord(book.getId(), patron.getId());
        if(bookingRecord == null)
            throw new RecordNotFoundException("Patron "+patron.getName()+" has not borrowed the book");
        bookingRecord.setReturnDate(new Date());
        this.bookingRecordRepository.save(bookingRecord);
    }
}

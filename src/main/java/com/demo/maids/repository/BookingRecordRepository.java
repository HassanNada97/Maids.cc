package com.demo.maids.repository;

import com.demo.maids.model.BookingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookingRecordRepository extends JpaRepository<BookingRecord, Integer> {
    @Query("select count(br)>0 from BookingRecord br where br.book.id=:bookId and br.patron.id=:patronId and br.returnDate is null ")
    boolean userAlreadyBorrowedBook(int bookId, int patronId);
    @Query("select br from BookingRecord br where br.book.id=:bookId and br.patron.id=:patronId and br.returnDate is null ")
    BookingRecord findBookingRecord(int bookId, int patronId);
}

package com.demo.maids.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "booking_records")
public class BookingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "borrowing_date")
    private Date borrowingDate;

    @Column(name = "return_date")
    private Date returnDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patron", nullable = false)
    private Patron patron;

}
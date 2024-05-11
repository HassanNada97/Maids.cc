package com.demo.maids.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "books")
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 65)
    @NotNull
    @Size(max = 65, message = "The Book Title must be less than 65 characters")
    private String title;

    @Column(name = "author", nullable = false, length = 45)
    @NotNull
    @Size(max = 45, message = "The Book Author must be less than 45 characters")
    private String author;

    @Column(name = "isbn", nullable = false, length = 20)
    @NotNull
    @Size(max = 20, message = "The Book ISBN must be less than 20 characters")
    private String isbn;

    @Column(name = "publication_year", nullable = false)
    @NotNull
    @Max(value = 2024, message = "Publication Year should not be greater than current year")
    private Short publicationYear;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"book", "patron"})
    private Set<BookingRecord> bookingRecords = new LinkedHashSet<>();

}
package com.demo.maids.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "patrons")
@ToString
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    @NotNull(message = "the Patron must have a name")
    @Size(max = 45, message = "The Patron name must be less than 45 characters")
    private String name;

    @NotNull(message = "the Patron must have a contact info")
    @Size(max = 45, message = "The Patron contact info must be less than 45 characters")
    @Column(name = "contact_info", nullable = false, length = 45)
    private String contactInfo;

    @OneToMany(mappedBy = "patron", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"book", "patron"})
    private Set<BookingRecord> bookingRecords = new LinkedHashSet<>();

}
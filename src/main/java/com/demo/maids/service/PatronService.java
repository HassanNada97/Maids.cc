package com.demo.maids.service;

import com.demo.maids.model.Patron;

import java.util.List;
import java.util.Optional;

public interface PatronService {
    Optional<Patron> findPatronById(int id);
    List<Patron> findAllPatrons();
    void deletePatron(int id);
    Patron createPatron(Patron patron);
    Patron updatePatron(Patron patron, int id);
}

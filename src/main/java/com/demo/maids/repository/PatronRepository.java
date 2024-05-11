package com.demo.maids.repository;

import com.demo.maids.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<Patron, Integer> {
}

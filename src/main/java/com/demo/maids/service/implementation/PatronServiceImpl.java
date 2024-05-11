package com.demo.maids.service.implementation;

import com.demo.maids.exception.RecordNotFoundException;
import com.demo.maids.model.Patron;
import com.demo.maids.repository.PatronRepository;
import com.demo.maids.service.PatronService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatronServiceImpl implements PatronService {
    private final PatronRepository patronRepository;

    public PatronServiceImpl(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }


    @Override
    public Optional<Patron> findPatronById(int id) {
        return this.patronRepository.findById(id);
    }

    @Override
    @Cacheable("patrons")
    public List<Patron> findAllPatrons() {
        System.err.println("accessing method find all patrons in service");
        return this.patronRepository.findAll();
    }

    @Override
    @CacheEvict(value = "patrons", allEntries = true)
    public void deletePatron(int id) {
        if(!this.patronRepository.existsById(id))
            throw new RecordNotFoundException("Patron with Id: "+id+" does not exist");
        this.patronRepository.deleteById(id);
    }

    @Override
    @Transactional
    @CacheEvict(value = "patrons", allEntries = true)
    public Patron createPatron(Patron patron) {
        return this.patronRepository.save(patron);
    }

    @Override
    @Transactional
    @CacheEvict(value = "patrons", allEntries = true)
    public Patron updatePatron(Patron patron, int id) {
        if(!this.patronRepository.existsById(id))
            throw new RecordNotFoundException("Patron with Id: "+id+" does not exist");
        patron.setId(id);
        this.patronRepository.save(patron);
        return patron;
    }
}

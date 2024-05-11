package com.demo.maids.controller;


import com.demo.maids.exception.RecordNotFoundException;
import com.demo.maids.model.Patron;
import com.demo.maids.service.PatronService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }


    @GetMapping
    public List<Patron> findAllPatrons(){
        return this.patronService.findAllPatrons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> findPatron(@PathVariable int id ){
        Optional<Patron> optionalPatron = this.patronService.findPatronById(id);
        if(optionalPatron.isEmpty())
            throw new RecordNotFoundException("Patron with Id: "+id+" does not exist");
        Patron patron = optionalPatron.get();
        return ResponseEntity.ok(patron);
    }

    @DeleteMapping("/{id}")
    public void deletePatron(@PathVariable int id){
        this.patronService.deletePatron(id);
    }

    @PutMapping("/{id}")
    public Patron updatePatron(@RequestBody @Valid Patron patron, @PathVariable int id){
        return this.patronService.updatePatron(patron, id);
    }

    @PostMapping
    public Patron createPatron(@RequestBody @Valid Patron patron){
        return this.patronService.createPatron(patron);
    }

}

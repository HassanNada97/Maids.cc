package com.demo.maids.controller;

import com.demo.maids.exception.RecordNotFoundException;
import com.demo.maids.model.Book;
import com.demo.maids.model.Patron;
import com.demo.maids.service.PatronService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PatronControllerTest {

    @Mock
    private PatronService patronService;

    @InjectMocks
    private PatronController patronController;



    @Test
    void testFindAllPatrons() {
        Patron mark = new Patron();
        mark.setName("Mark");
        Patron john = new Patron();
        mark.setName("John");

        List<Patron> expectedPatrons = Arrays.asList(mark, john);
        Mockito.when(patronService.findAllPatrons()).thenReturn(expectedPatrons);

        List<Patron> actualPatrons = patronController.findAllPatrons();
        System.err.println("actual patrons "+actualPatrons);

        assertEquals(expectedPatrons, actualPatrons);
    }

    @Test
    public void testFindPatronSuccess() {
        int patronId = 1;
        Patron exectedPatron = new Patron();
        exectedPatron.setName("Mark");
        exectedPatron.setId(patronId);
        Mockito.when(patronService.findPatronById(patronId)).thenReturn(Optional.of(exectedPatron));

        ResponseEntity<Patron> responseEntity = patronController.findPatron(patronId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(exectedPatron, responseEntity.getBody());
        System.err.println("response entity: "+responseEntity.getBody());
    }

    @Test
    public void testFindPatronNotFound() {
        int patronId = 1;
        Mockito.when(patronService.findPatronById(patronId)).thenReturn(Optional.empty());

        try {
            patronController.findPatron(patronId);
            fail("Expected RecordNotFoundException");
        } catch (RecordNotFoundException e) {
            assertEquals("Patron with Id: "+patronId+" does not exist", e.getMessage());
        }
    }

    @Test
    public void testDeletePatron() {
        int patronId = 1;

        Mockito.doNothing().when(patronService).deletePatron(patronId);

        patronController.deletePatron(patronId);


        Mockito.verify(patronService, times(1)).deletePatron(patronId);
    }


    @Test
    public void testUpdatePatron() {
        int patronId = 1;
        Patron updatedPatron = new Patron();
        updatedPatron.setName("Updated Patron");
        updatedPatron.setId(patronId);


        Mockito.when(patronService.updatePatron(updatedPatron, patronId)).thenReturn(updatedPatron);

        Patron returnedPatron = patronController.updatePatron(updatedPatron, patronId);

        assertEquals(updatedPatron, returnedPatron);
    }



    @Test
    public void testCreatePatron() {
        Patron newPatron = new Patron();
        newPatron.setName("New Patron");

        // Mock successful creation by bookService
        Mockito.when(patronService.createPatron(newPatron)).thenReturn(newPatron);

        Patron createdPatron = patronController.createPatron(newPatron);

        assertEquals(newPatron, createdPatron);
    }
}
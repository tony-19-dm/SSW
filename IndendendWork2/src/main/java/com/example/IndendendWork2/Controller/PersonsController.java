package com.example.IndendendWork2.Controller;

import com.example.IndendendWork2.Model.Person;
import com.example.IndendendWork2.Service.PersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/persons")
public class PersonsController {
    private final PersonsService personsService;

    @Autowired
    public PersonsController(PersonsService personsService) {
        this.personsService = personsService;
    }

    @GetMapping
    public List<Person> getAllPersons(){
        return personsService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> addPerson(@RequestBody Person person){
        Person newPerson = personsService.addPerson(person);
        return ResponseEntity.accepted().build();
    }
}

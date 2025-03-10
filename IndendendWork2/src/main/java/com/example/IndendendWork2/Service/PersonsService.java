package com.example.IndendendWork2.Service;

import com.example.IndendendWork2.Model.Person;
import com.example.IndendendWork2.Repository.PersonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonsService {
    private final PersonsRepository personsRepository;

    @Autowired
    public PersonsService(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }

    public List<Person> getAll(){
        return personsRepository.findAll();
    }

    public Person addPerson(Person person){
        return personsRepository.save(person);
    }
}

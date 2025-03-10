package com.example.IndendendWork2.Repository;

import com.example.IndendendWork2.Model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonsRepositoryImpl implements PersonsRepository{
    private final List<Person> persons = new ArrayList<>();

    @Override
    public List<Person> findAll() {
        return persons;
    }

    @Override
    public Person save(Person person) {
        persons.add(person);
        return person;
    }
}

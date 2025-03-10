package com.example.IndendendWork2.Repository;

import com.example.IndendendWork2.Model.Person;

import java.util.List;

public interface PersonsRepository {
    List<Person> findAll();
    Person save(Person person);
}
package com.example.PersonCars.Repository;

import com.example.PersonCars.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>{
}

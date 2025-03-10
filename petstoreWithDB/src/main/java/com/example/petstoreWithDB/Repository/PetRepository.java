package com.example.petstoreWithDB.Repository;

import com.example.petstoreWithDB.Model.Pet;

import java.util.List;
import java.util.Optional;

public interface PetRepository {
    List<Pet> findAll();
    Optional<Pet> findById(Long id);
    Pet save(Pet pet);
    Pet deleteById(Long id);
    Pet update(Pet pet);
}
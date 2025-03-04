package com.example.petstore.repository;

import com.example.petstore.model.Pet;

import java.util.List;
import java.util.Optional;

public interface PetRepository {
    List<Pet> findAll();
    Optional<Pet> findById(Long id);
    Pet save(Pet pet);
    void deleteById(Long id);
    Pet update(Pet pet);
}


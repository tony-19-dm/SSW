package com.example.petstore.service;

import com.example.petstore.exception.InvalidIdException;
import com.example.petstore.exception.PetNotFoundException;
import com.example.petstore.model.Pet;
import com.example.petstore.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }

    public Pet updatePet(Pet pet) {
        if (pet.getId() == null || pet.getId() <= 0) {
            throw new InvalidIdException("Invalid ID supplied"); //400
        }

        if (!petRepository.findById(pet.getId()).isPresent()) {
            throw new PetNotFoundException("Pet not found with id: " + pet.getId()); //404
        }

        return petRepository.update(pet);
    }
}

package com.example.petstoreWithDB.Service;

import com.example.petstoreWithDB.Exception.InvalidIdException;
import com.example.petstoreWithDB.Exception.PetNotFoundException;
import com.example.petstoreWithDB.Exception.ValidationException;
import com.example.petstoreWithDB.Model.Pet;
import com.example.petstoreWithDB.Repository.PetRepository;
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
        //return petRepository.findById(id);
        if (id == null || id < 0){
            throw new InvalidIdException("Invalid ID supplied"); // 400
        }

        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isEmpty()){
            throw new PetNotFoundException("Pet not found with id: " + id); // 404
        }
        return pet;
    }

    public Pet addPet(Pet pet) {
        if (pet.getName() == null || pet.getName().isEmpty()) {
            throw new ValidationException("Validation exception: Pet name is required"); // 422
        }

        if (pet.getId() == null && pet.getId() <= 0) {
            throw new InvalidIdException("Invalid input: ID must be positive"); // 400
        }

        return petRepository.save(pet);
    }

    public Pet deletePet(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidIdException("Invalid ID supplied"); // 400
        }
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isEmpty()) {
            throw new PetNotFoundException("Pet not found with id: " + id); // 404
        }
        return petRepository.deleteById(id);
    }

    public Pet updatePet(Pet pet) {
        if (pet.getId() == null || pet.getId() <= 0) {
            throw new InvalidIdException("Invalid ID supplied"); //400
        }
        if (!petRepository.findById(pet.getId()).isPresent()) {
            throw new PetNotFoundException("Pet not found with id: " + pet.getId()); //404
        }
        if (pet.getName() == null || pet.getName().isEmpty()){
            throw new ValidationException("Validation exception: Pet name is required"); // 422
        }
        return petRepository.update(pet);
    }
}
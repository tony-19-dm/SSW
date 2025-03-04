package com.example.petstore.repository;

import com.example.petstore.model.Pet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PetRepositoryImpl implements PetRepository{
    private final List<Pet> pets = new ArrayList<>();

    @Override
    public List<Pet> findAll() {
        return pets;
    }

    @Override
    public Optional<Pet> findById(Long id) {
        return pets.stream().filter(pet -> pet.getId().equals(id)).findFirst();
    }

    @Override
    public Pet save(Pet pet) {
        pets.add(pet);
        return pet;
    }

    @Override
    public void deleteById(Long id) {
        pets.removeIf(pet -> pet.getId().equals(id));
    }

    @Override
    public Pet update(Pet pet){
        Optional<Pet> existingPet = pets.stream()
                .filter(p -> p.getId().equals(pet.getId()))
                .findFirst();

        Pet oldPet = existingPet.get();
        oldPet.setId(pet.getId());
        oldPet.setName(pet.getName());
        oldPet.setCategory(pet.getCategory());
        oldPet.setTags(pet.getTags());
        oldPet.setStatus(pet.getStatus());
        return oldPet;
    }
}

package com.example.CustomerWitchAutorisation.repository;

import com.example.CustomerWitchAutorisation.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}

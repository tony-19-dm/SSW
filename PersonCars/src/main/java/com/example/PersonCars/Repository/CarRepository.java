package com.example.PersonCars.Repository;

import com.example.PersonCars.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository <Car, Long>{
}

package com.example.PersonCars.Service;

import com.example.PersonCars.Model.Car;
import com.example.PersonCars.Model.Person;
import com.example.PersonCars.Repository.CarRepository;
import com.example.PersonCars.Repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final CarRepository carRepository;

    // Вложенные классы исключений
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class PersonNotFoundException extends RuntimeException {
        public PersonNotFoundException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public static class ValidationException extends RuntimeException {
        public ValidationException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class InvalidIdException extends RuntimeException {
        public InvalidIdException(String message) {
            super(message);
        }
    }

    @Autowired
    public PersonService(PersonRepository personRepository, CarRepository carRepository) {
        this.personRepository = personRepository;
        this.carRepository = carRepository;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(Long id) {
        if (id == null || id < 0) {
            throw new InvalidIdException("Invalid ID supplied");
        }

        Optional<Person> person = personRepository.findById(id);
        if (person.isEmpty()) {
            throw new PersonNotFoundException("Person not found with id: " + id);
        }
        return person;
    }

    @Transactional
    public Person addPerson(Person person) {
        if (person.getName() == null || person.getName().isEmpty()) {
            throw new ValidationException("Validation exception: Person name is required");
        }

        // Обрабатываем автомобили
        if(person.getCars() != null) {
            // Для каждого автомобиля проверяем существование
            person.getCars().forEach(car -> {
                if(car.getId() != null) {
                    // Проверяем существование авто в БД
                    Car existingCar = carRepository.findById(car.getId())
                            .orElseThrow(() -> new ValidationException("Car not found with id: " + car.getId()));

                    // Обновляем поля существующего авто
                    existingCar.setManufactures(car.getManufactures());
                    existingCar.setVelocity(car.getVelocity());
                    existingCar.setKmRun(car.getKmRun());
                    carRepository.save(existingCar);
                } else {
                    // Сохраняем новое авто
                    carRepository.save(car);
                }
            });
        }

        return personRepository.save(person);
    }

    @Transactional
    public void deletePerson(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidIdException("Invalid ID supplied");
        }

        if (!personRepository.existsById(id)) {
            throw new PersonNotFoundException("Person not found with id: " + id);
        }

        personRepository.deleteById(id);
    }

    @Transactional
    public Person updatePerson(Person person) {
        if (person.getId() == null || person.getId() <= 0) {
            throw new InvalidIdException("Invalid ID supplied");
        }

        if (!personRepository.existsById(person.getId())) {
            throw new PersonNotFoundException("Person not found with id: " + person.getId());
        }

        if (person.getName() == null || person.getName().isEmpty()) {
            throw new ValidationException("Validation exception: Person name is required");
        }

        return personRepository.save(person);
    }
}

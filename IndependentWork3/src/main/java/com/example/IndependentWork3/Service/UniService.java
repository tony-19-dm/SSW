package com.example.IndependentWork3.Service;

import com.example.IndependentWork3.Model.Institute;
import com.example.IndependentWork3.Model.University;
import com.example.IndependentWork3.Repository.InstRepository;
import com.example.IndependentWork3.Repository.UniRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@Service
public class UniService {
    private final UniRepository uniRepository;
    private final InstRepository instRepository;

    @Autowired
    public UniService(UniRepository uniRepository, InstRepository instRepository) {
        this.uniRepository = uniRepository;
        this.instRepository = instRepository;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class UniversityNotFoundException extends RuntimeException {
        public UniversityNotFoundException(String message) {
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

    public List<University> getAllUni() {
        return uniRepository.findAll();
    }

    public Optional<University> getUniById(Long id) {
        if (id == null || id < 0) {
            throw new InvalidIdException("Invalid ID supplied");
        }

        Optional<University> university = uniRepository.findById(id);
        if (university.isEmpty()) {
            throw new UniversityNotFoundException("University not found with id: " + id);
        }
        return university;
    }

    @Transactional
    public University addUni(University university) {
        if (university.getName() == null || university.getName().isEmpty()) {
            throw new ValidationException("Validation exception: Person name is required");
        }

        // Обрабатываем автомобили
        if(university.getInstitutes() != null) {
            // Для каждого автомобиля проверяем существование
            university.getInstitutes().forEach(inst -> {
                if(inst.getId() != null) {
                    // Проверяем существование авто в БД
                    Institute existingInst = instRepository.findById(inst.getId())
                            .orElseThrow(() -> new ValidationException("Institute not found with id: " + inst.getId()));

                    // Обновляем поля существующего авто
                    existingInst.setName(inst.getName());
                    existingInst.setPhone(inst.getPhone());
                    instRepository.save(existingInst);
                } else {
                    // Сохраняем новое авто
                    instRepository.save(inst);
                }
            });
        }

        return uniRepository.save(university);
    }

    @Transactional
    public void deleteUni(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidIdException("Invalid ID supplied");
        }

        if (!uniRepository.existsById(id)) {
            throw new UniversityNotFoundException("University not found with id: " + id);
        }

        uniRepository.deleteById(id);
    }

    @Transactional
    public University updateUni(University university) {
        if (university.getId() == null || university.getId() <= 0) {
            throw new InvalidIdException("Invalid ID supplied");
        }

        if (!uniRepository.existsById(university.getId())) {
            throw new UniversityNotFoundException("University not found with id: " + university.getId());
        }

        if (university.getName() == null || university.getName().isEmpty()) {
            throw new ValidationException("Validation exception: University name is required");
        }

        return uniRepository.save(university);
    }
}

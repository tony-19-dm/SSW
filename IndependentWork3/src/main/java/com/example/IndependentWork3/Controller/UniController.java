package com.example.IndependentWork3.Controller;

import com.example.IndependentWork3.Model.University;
import com.example.IndependentWork3.Service.UniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/uni")
public class UniController {
    private final UniService uniService;

    @Autowired
    public UniController(UniService uniService) {
        this.uniService = uniService;
    }

    @GetMapping
    public List<University> getAllUni() {
        return uniService.getAllUni();
    }

    @GetMapping("/{uniId}")
    public ResponseEntity<University> getUniById(@PathVariable Long uniId) {
        return ResponseEntity.of(uniService.getUniById(uniId));
    }

    @PostMapping
    public University addUni(@RequestBody University university) {
        return uniService.addUni(university);
    }

    @PutMapping
    public University updateUni(@RequestBody University university) {
        return uniService.updateUni(university);
    }

    @DeleteMapping("/{uniId}")
    public ResponseEntity<Void> deleteUni(@PathVariable Long uniId) {
        uniService.deleteUni(uniId);
        return ResponseEntity.noContent().build();
    }
}

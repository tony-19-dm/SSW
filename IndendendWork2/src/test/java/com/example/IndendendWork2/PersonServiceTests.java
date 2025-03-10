package com.example.IndendendWork2;

import com.example.IndendendWork2.Model.Person;
import com.example.IndendendWork2.Repository.PersonsRepository;
import com.example.IndendendWork2.Service.PersonsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class PersonServiceTests {
    @Mock
    private PersonsRepository personsRepository;

    @InjectMocks
    private PersonsService personsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPets() {
        Person person1 = new Person();
        person1.setId(1L);
        person1.setAge(20);
        person1.setName("Tony");
        Person person2 = new Person();
        person2.setId(2L);
        person2.setName("Anton");
        person2.setAge(21);
        List<Person> persons = Arrays.asList(person1, person2);

        when(personsRepository.findAll()).thenReturn(persons);

        List<Person> result = personsService.getAll();

        assertEquals(2, result.size());
        assertEquals("Tony", result.get(0).getName());
        assertEquals("Anton", result.get(1).getName());
        verify(personsRepository, times(1)).findAll();
    }

    @Test
    void addPersonTest(){
        Person person = new Person();
        person.setId(1L);
        person.setName("Tony");
        person.setAge(20);

        when(personsRepository.save(person)).thenReturn(person);

        Person result = personsService.addPerson(person);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Tony", result.getName());
        assertEquals(20, result.getAge());
        verify(personsRepository, times(1)).save(person);
    }
}

package com.example.IndendendWork2;

import com.example.IndendendWork2.Controller.PersonsController;
import com.example.IndendendWork2.Model.Person;
import com.example.IndendendWork2.Service.PersonsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PrersonControllerTests {
    private MockMvc mockMvc;

    @Mock
    private PersonsService personsService;

    @InjectMocks
    private PersonsController personsController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(personsController).build();
    }

    @Test
    void getAllPersons() throws Exception {
        Person person1 = new Person();
        person1.setId(1L);
        person1.setAge(20);
        person1.setName("Tony");
        Person person2 = new Person();
        person2.setId(2L);
        person2.setName("Anton");
        person2.setAge(21);
        List<Person> persons = Arrays.asList(person1, person2);

        when(personsService.getAll()).thenReturn(persons);

        mockMvc.perform(get("/api/v3/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[0].name").value("Tony"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].age").value(21))
                .andExpect(jsonPath("$[1].name").value("Anton"));
        verify(personsService, times(1)).getAll();
    }

    @Test
    void addPerson() throws Exception {
        Person person = new Person();
        person.setId(1L);
        person.setName("Tony");
        person.setAge(20);

        when(personsService.addPerson(any(Person.class))).thenReturn(person);

        mockMvc.perform(post("/api/v3/persons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isAccepted());

        verify(personsService, times(1)).addPerson(any(Person.class));
    }
}

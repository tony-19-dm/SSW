package com.example.petstore;

import com.example.petstore.controller.PetController;
import com.example.petstore.model.Pet;
import com.example.petstore.service.PetService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PetController.class)
public class PetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @Test
    public void testGetPetById() throws Exception {
        Pet pet = new Pet();
        pet.setId(1L);
        when(petService.getPetById(1L)).thenReturn(Optional.of(pet));

        mockMvc.perform(get("/api/v3/pet/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }
}

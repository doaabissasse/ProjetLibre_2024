package com.example.contactes_service;

import com.example.contactes_service.Entite.contacte;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ContacteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // For converting objects to JSON and vice versa

    @Test
    public void testAddContacte() throws Exception {
        contacte newContacte = new contacte(0, 1L, "123456789", "987654321", "test@example.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/contactes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newContacte)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.tel").value("123456789"));
    }

    @Test
    public void testGetAllContactes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/contactes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").isNotEmpty());
    }

    @Test
    public void testFindContactesByLaboratoire() throws Exception {
        long laboId = 1;

        mockMvc.perform(MockMvcRequestBuilders.get("/contactes/laboratoire/" + laboId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").isNotEmpty());
    }

    @Test
    public void testUpdateContacte() throws Exception {
        long id = 1;
        contacte updatedContacte = new contacte(1, 1L, "111222333", "444555666", "updated@example.com");

        mockMvc.perform(MockMvcRequestBuilders.put("/contactes/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedContacte)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tel").value("111222333"));
    }

    @Test
    public void testDeleteContacte() throws Exception {
        long id = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/contactes/" + id))
                .andExpect(status().isOk());
    }
}
package com.example.epreuve_service;

import com.example.epreuve_service.controller.EpreuveController;
import com.example.epreuve_service.entity.epreuve;
import com.example.epreuve_service.service.EpreuveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EpreuveControllerTest {

    @Mock
    private EpreuveService epreuveService;

    @InjectMocks
    private EpreuveController epreuveController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEpreuves_ShouldReturnAllEpreuves() {
        // Arrange
        when(epreuveService.getAllEpreuves()).thenReturn(
                Arrays.asList(new epreuve(1L, 101L, "Test 1"), new epreuve(2L, 102L, "Test 2"))
        );

        // Act
        List<epreuve> epreuves = epreuveController.getAllEpreuves();

        // Assert
        assertEquals(2, epreuves.size());
        verify(epreuveService, times(1)).getAllEpreuves();
    }

    @Test
    void getEpreuveById_ShouldReturnEpreuveIfFound() {
        // Arrange
        Long id = 1L;
        epreuve epreuve = new epreuve(1L, 101L, "Test Epreuve");
        when(epreuveService.getEpreuveById(id)).thenReturn(epreuve);

        // Act
        ResponseEntity<epreuve> response = epreuveController.getEpreuveById(id);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test Epreuve", response.getBody().getNom());
        verify(epreuveService, times(1)).getEpreuveById(id);
    }

    @Test
    void createEpreuve_ShouldReturnCreatedEpreuve() {
        // Arrange
        epreuve epreuve = new epreuve(null, 101L, "New Epreuve");
        when(epreuveService.createEpreuve(epreuve)).thenReturn(new epreuve(1L, 101L, "New Epreuve"));

        // Act
        ResponseEntity<epreuve> response = epreuveController.createEpreuve(epreuve);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("New Epreuve", response.getBody().getNom());
        verify(epreuveService, times(1)).createEpreuve(epreuve);
    }
}

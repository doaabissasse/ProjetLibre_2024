package com.example.examen_service;

import com.example.examen_service.controller.ExamenController;
import com.example.examen_service.entity.Examen;
import com.example.examen_service.service.ExamenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExamenControllerTest {

    @Mock
    private ExamenService examenService;

    @InjectMocks
    private ExamenController examenController;

    private Examen examen;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        examen = new Examen(1L, 101L, 201L, 301L, "Passed");
    }

    @Test
    void createExamen_ShouldReturnCreatedExamen() {
        // Arrange
        when(examenService.saveExamen(examen)).thenReturn(examen);

        // Act
        ResponseEntity<Examen> response = examenController.createExamen(examen);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(examen, response.getBody());
        verify(examenService, times(1)).saveExamen(examen);
    }

    @Test
    void getExamenById_ShouldReturnExamenIfFound() {
        // Arrange
        when(examenService.getExamenById(1L)).thenReturn(Optional.of(examen));

        // Act
        ResponseEntity<Examen> response = examenController.getExamenById(1L);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(examen, response.getBody());
        verify(examenService, times(1)).getExamenById(1L);
    }

    @Test
    void getExamenById_ShouldReturnNotFoundWhenExamenNotFound() {
        // Arrange
        when(examenService.getExamenById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Examen> response = examenController.getExamenById(1L);

        // Assert
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(examenService, times(1)).getExamenById(1L);
    }

    @Test
    void getAllExamens_ShouldReturnListOfExamens() {
        // Arrange
        Examen examen2 = new Examen(2L, 102L, 202L, 302L, "Failed");
        List<Examen> examens = Arrays.asList(examen, examen2);
        when(examenService.getAllExamens()).thenReturn(examens);

        // Act
        ResponseEntity<List<Examen>> response = examenController.getAllExamens();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        assertTrue(response.getBody().contains(examen));
        assertTrue(response.getBody().contains(examen2));
        verify(examenService, times(1)).getAllExamens();
    }

    @Test
    void updateExamen_ShouldReturnUpdatedExamen() {
        // Arrange
        Examen updatedExamen = new Examen(1L, 102L, 202L, 302L, "Updated");
        when(examenService.updateExamen(1L, updatedExamen)).thenReturn(updatedExamen);

        // Act
        ResponseEntity<Examen> response = examenController.updateExamen(1L, updatedExamen);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedExamen, response.getBody());
        verify(examenService, times(1)).updateExamen(1L, updatedExamen);
    }

    @Test
    void updateExamen_ShouldReturnNotFoundWhenExamenNotFound() {
        // Arrange
        Examen updatedExamen = new Examen(1L, 102L, 202L, 302L, "Updated");
        when(examenService.updateExamen(1L, updatedExamen)).thenThrow(new RuntimeException());

        // Act
        ResponseEntity<Examen> response = examenController.updateExamen(1L, updatedExamen);

        // Assert
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(examenService, times(1)).updateExamen(1L, updatedExamen);
    }

    @Test
    void deleteExamen_ShouldReturnNoContent() {
        // Arrange
        doNothing().when(examenService).deleteExamen(1L);

        // Act
        ResponseEntity<Void> response = examenController.deleteExamen(1L);

        // Assert
        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(examenService, times(1)).deleteExamen(1L);
    }
}


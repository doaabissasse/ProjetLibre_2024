package com.example.examen_service;

import com.example.examen_service.entity.Examen;
import com.example.examen_service.repository.ExamenRepository;
import com.example.examen_service.service.ExamenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExamenServiceTest {

    @Mock
    private ExamenRepository examenRepository;

    @InjectMocks
    private ExamenService examenService;

    private Examen examen;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        examen = new Examen(1L, 101L, 201L, 301L, "Passed");
    }

    @Test
    void createExamen_ShouldReturnSavedExamen() {
        // Arrange
        when(examenRepository.save(examen)).thenReturn(examen);

        // Act
        Examen createdExamen = examenService.saveExamen(examen);

        // Assert
        assertNotNull(createdExamen);
        assertEquals(examen, createdExamen);
        verify(examenRepository, times(1)).save(examen);
    }

    @Test
    void getExamenById_ShouldReturnExamenIfFound() {
        // Arrange
        when(examenRepository.findById(1L)).thenReturn(Optional.of(examen));

        // Act
        Optional<Examen> foundExamen = examenService.getExamenById(1L);

        // Assert
        assertTrue(foundExamen.isPresent());
        assertEquals(examen, foundExamen.orElse(null));
        verify(examenRepository, times(1)).findById(1L);
    }

    @Test
    void updateExamen_ShouldThrowExceptionWhenExamenNotFound() {
        // Arrange
        when(examenRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> examenService.updateExamen(1L, examen));
        verify(examenRepository, times(1)).findById(1L);
    }

    @Test
    void deleteExamen_ShouldCallRepositoryDeleteById() {
        // Arrange
        when(examenRepository.findById(1L)).thenReturn(Optional.of(examen));

        // Act
        examenService.deleteExamen(1L);

        // Assert
        verify(examenRepository, times(1)).deleteById(1L);
    }

    @Test
    void getAllExamens_ShouldReturnListOfExamens() {
        // Arrange
        Examen examen2 = new Examen(2L, 102L, 202L, 302L, "Failed");
        List<Examen> examens = Arrays.asList(examen, examen2);
        when(examenRepository.findAll()).thenReturn(examens);

        // Act
        List<Examen> result = examenService.getAllExamens();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(examen));
        assertTrue(result.contains(examen2));
        verify(examenRepository, times(1)).findAll();
    }
}


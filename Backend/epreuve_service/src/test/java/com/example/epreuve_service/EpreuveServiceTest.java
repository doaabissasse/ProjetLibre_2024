package com.example.epreuve_service;

import com.example.epreuve_service.entity.epreuve;
import com.example.epreuve_service.repository.EpreuveRepository;
import com.example.epreuve_service.service.EpreuveService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EpreuveServiceTest {

    @Mock
    private EpreuveRepository epreuveRepository;

    @InjectMocks
    private EpreuveService epreuveService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEpreuves_ShouldReturnAllEpreuves() {
        // Arrange
        when(epreuveRepository.findAll()).thenReturn(
                Arrays.asList(new epreuve(1L, 101L, "Test 1"), new epreuve(2L, 102L, "Test 2"))
        );

        // Act
        var epreuves = epreuveService.getAllEpreuves();

        // Assert
        assertEquals(2, epreuves.size());
        verify(epreuveRepository, times(1)).findAll();
    }

    @Test
    void getEpreuveById_ShouldReturnEpreuveIfFound() {
        // Arrange
        Long id = 1L;
        epreuve epreuve = new epreuve(1L, 101L, "Test Epreuve");
        when(epreuveRepository.findById(id)).thenReturn(Optional.of(epreuve));

        // Act
        epreuve foundEpreuve = epreuveService.getEpreuveById(id);

        // Assert
        assertNotNull(foundEpreuve);
        assertEquals("Test Epreuve", foundEpreuve.getNom());
        verify(epreuveRepository, times(1)).findById(id);
    }

    @Test
    void createEpreuve_ShouldSaveAndReturnEpreuve() {
        // Arrange
        epreuve epreuve = new epreuve(null, 101L, "New Epreuve");
        when(epreuveRepository.save(epreuve)).thenReturn(new epreuve(1L, 101L, "New Epreuve"));

        // Act
        epreuve savedEpreuve = epreuveService.createEpreuve(epreuve);

        // Assert
        assertNotNull(savedEpreuve.getId());
        assertEquals("New Epreuve", savedEpreuve.getNom());
        verify(epreuveRepository, times(1)).save(epreuve);
    }

    @Test
    void updateEpreuve_ShouldUpdateEpreuveIfExists() {
        // Arrange
        Long id = 1L;
        epreuve existingEpreuve = new epreuve(1L, 101L, "Old Epreuve");
        epreuve updatedEpreuve = new epreuve(null, 102L, "Updated Epreuve");

        when(epreuveRepository.findById(id)).thenReturn(Optional.of(existingEpreuve));
        when(epreuveRepository.save(any(epreuve.class))).thenReturn(new epreuve(1L, 102L, "Updated Epreuve"));

        // Act
        epreuve result = epreuveService.updateEpreuve(id, updatedEpreuve);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Epreuve", result.getNom());
        assertEquals(102L, result.getIdAnalyse());
        verify(epreuveRepository, times(1)).findById(id);
        verify(epreuveRepository, times(1)).save(any(epreuve.class));
    }

    @Test
    void deleteEpreuve_ShouldCallRepositoryDeleteById() {
        // Arrange
        Long id = 1L;

        // Act
        epreuveService.deleteEpreuve(id);

        // Assert
        verify(epreuveRepository, times(1)).deleteById(id);
    }
}

package com.example.dossiersService;

import com.example.dossiersService.controller.DossierController;
import com.example.dossiersService.entity.Dossier;
import com.example.dossiersService.service.DossierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DossierControllerTest {

    @Mock
    private DossierService dossierService;

    @InjectMocks
    private DossierController dossierController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createDossier_ShouldReturnCreatedDossier() {
        // Arrange
        Dossier dossier = new Dossier(null, 1L, 2L, null);
        when(dossierService.createDossier(dossier)).thenReturn(new Dossier(1L, 1L, 2L, null));

        // Act
        ResponseEntity<Dossier> response = dossierController.createDossier(dossier);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        assertEquals(1L, response.getBody().getIdPatient());
        verify(dossierService, times(1)).createDossier(dossier);
    }

    @Test
    void getDossierById_ShouldReturnDossierIfFound() {
        // Arrange
        Long id = 1L;
        Dossier dossier = new Dossier(id, 1L, 2L, null);
        when(dossierService.getDossierById(id)).thenReturn(Optional.of(dossier));

        // Act
        ResponseEntity<Dossier> response = dossierController.getDossierById(id);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(id, response.getBody().getId());
        verify(dossierService, times(1)).getDossierById(id);
    }

    @Test
    void getDossierById_ShouldReturnNotFoundIfNotExists() {
        // Arrange
        Long id = 1L;
        when(dossierService.getDossierById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Dossier> response = dossierController.getDossierById(id);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
        verify(dossierService, times(1)).getDossierById(id);
    }

    @Test
    void getDossiersByPatientId_ShouldReturnListOfDossiers() {
        // Arrange
        Long idPatient = 1L;
        Dossier dossier1 = new Dossier(1L, idPatient, 2L, null);
        Dossier dossier2 = new Dossier(2L, idPatient, 3L, null);
        when(dossierService.getDossiersByPatientId(idPatient)).thenReturn(Arrays.asList(dossier1, dossier2));

        // Act
        ResponseEntity<List<Dossier>> response = dossierController.getDossiersByPatientId(idPatient);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(dossierService, times(1)).getDossiersByPatientId(idPatient);
    }

    @Test
    void updateDossier_ShouldReturnUpdatedDossier() {
        // Arrange
        Long id = 1L;
        Dossier updatedDossier = new Dossier(null, 2L, 3L, null);
        when(dossierService.updateDossier(id, updatedDossier)).thenReturn(new Dossier(id, 2L, 3L, null));

        // Act
        ResponseEntity<Dossier> response = dossierController.updateDossier(id, updatedDossier);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2L, response.getBody().getIdPatient());
        assertEquals(3L, response.getBody().getIdUtilisateur());
        verify(dossierService, times(1)).updateDossier(id, updatedDossier);
    }

    @Test
    void deleteDossier_ShouldReturnNoContent() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<Void> response = dossierController.deleteDossier(id);

        // Assert
        assertEquals(204, response.getStatusCodeValue());
        verify(dossierService, times(1)).deleteDossier(id);
    }
}
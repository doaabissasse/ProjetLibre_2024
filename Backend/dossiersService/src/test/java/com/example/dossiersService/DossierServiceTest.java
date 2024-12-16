package com.example.dossiersService;

import com.example.dossiersService.entity.Dossier;
import com.example.dossiersService.repository.DossierRepository;
import com.example.dossiersService.service.DossierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DossierServiceTest {

    @Mock
    private DossierRepository dossierRepository;

    @InjectMocks
    private DossierService dossierService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createDossier_ShouldSaveAndReturnDossier() {
        // Arrange
        Dossier dossier = new Dossier(null, 1L, 2L, null);
        when(dossierRepository.save(dossier)).thenReturn(new Dossier(1L, 1L, 2L, LocalDate.now()));

        // Act
        Dossier createdDossier = dossierService.createDossier(dossier);

        // Assert
        assertNotNull(createdDossier.getId());
        assertEquals(1L, createdDossier.getIdPatient());
        assertEquals(2L, createdDossier.getIdUtilisateur());
        verify(dossierRepository, times(1)).save(dossier);
    }

    @Test
    void getDossierById_ShouldReturnDossierIfFound() {
        // Arrange
        Long id = 1L;
        Dossier dossier = new Dossier(1L, 1L, 2L, null);
        when(dossierRepository.findById(id)).thenReturn(Optional.of(dossier));

        // Act
        Dossier foundDossier = dossierService.getDossierById(id).orElse(null);

        // Assert
        assertNotNull(foundDossier);
        assertEquals(id, foundDossier.getId());
        verify(dossierRepository, times(1)).findById(id);
    }

    @Test
    void getDossierById_ShouldReturnEmptyIfNotFound() {
        // Arrange
        Long id = 1L;
        when(dossierRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<Dossier> foundDossier = dossierService.getDossierById(id);

        // Assert
        assertFalse(foundDossier.isPresent());
        verify(dossierRepository, times(1)).findById(id);
    }

    @Test
    void getDossiersByPatientId_ShouldReturnListOfDossiers() {
        // Arrange
        Long idPatient = 1L;
        Dossier dossier1 = new Dossier(1L, idPatient, 2L, LocalDate.now());
        Dossier dossier2 = new Dossier(2L, idPatient, 3L, LocalDate.now());
        when(dossierRepository.findByIdPatient(idPatient)).thenReturn(Arrays.asList(dossier1, dossier2));

        // Act
        List<Dossier> dossiers = dossierService.getDossiersByPatientId(idPatient);

        // Assert
        assertEquals(2, dossiers.size());
        assertTrue(dossiers.stream().allMatch(d -> d.getIdPatient().equals(idPatient)));
        verify(dossierRepository, times(1)).findByIdPatient(idPatient);
    }

    @Test
    void updateDossier_ShouldUpdateAndReturnDossier() {
        // Arrange
        Long id = 1L;
        Dossier existingDossier = new Dossier(id, 1L, 2L, LocalDate.now());
        Dossier updatedDossier = new Dossier(null, 2L, 3L, LocalDate.now().plusDays(1));

        when(dossierRepository.findById(id)).thenReturn(Optional.of(existingDossier));
        when(dossierRepository.save(any(Dossier.class))).thenReturn(new Dossier(id, 2L, 3L, LocalDate.now().plusDays(1)));

        // Act
        Dossier result = dossierService.updateDossier(id, updatedDossier);

        // Assert
        assertNotNull(result);
        assertEquals(2L, result.getIdPatient());
        assertEquals(3L, result.getIdUtilisateur());
        assertEquals(LocalDate.now().plusDays(1), result.getDate());
        verify(dossierRepository, times(1)).findById(id);
        verify(dossierRepository, times(1)).save(any(Dossier.class));
    }

    @Test
    void deleteDossier_ShouldCallRepositoryDeleteById() {
        // Arrange
        Long id = 1L;

        // Act
        dossierService.deleteDossier(id);

        // Assert
        verify(dossierRepository, times(1)).deleteById(id);
    }
}
package com.example.adresses_service;

import com.example.adresses_service.Entite.Adresse;
import com.example.adresses_service.repository.AdresseRepository;
import com.example.adresses_service.service.AdresseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdresseServiceTest {

    @InjectMocks
    private AdresseService adresseService;

    @Mock
    private AdresseRepository adresseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAdresses() {
        // Arrange
        Adresse adresse1 = new Adresse(1L, "1", "Rue A", "75001", "Paris", "Commune A");
        Adresse adresse2 = new Adresse(2L, "2", "Rue B", "75002", "Paris", "Commune B");
        when(adresseRepository.findAll()).thenReturn(Arrays.asList(adresse1, adresse2));

        // Act
        List<Adresse> adresses = adresseService.getAllAdresses();

        // Assert
        assertNotNull(adresses);
        assertEquals(2, adresses.size());
        verify(adresseRepository, times(1)).findAll();
    }

    @Test
    void testGetAdresseById() {
        // Arrange
        Adresse adresse = new Adresse(1L, "1", "Rue A", "75001", "Paris", "Commune A");
        when(adresseRepository.findById(1L)).thenReturn(Optional.of(adresse));

        // Act
        Optional<Adresse> result = adresseService.getAdresseById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Rue A", result.get().getNomVoie());
        verify(adresseRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveAdresse() {
        // Arrange
        Adresse adresse = new Adresse(null, "1", "Rue A", "75001", "Paris", "Commune A");
        Adresse savedAdresse = new Adresse(1L, "1", "Rue A", "75001", "Paris", "Commune A");
        when(adresseRepository.save(adresse)).thenReturn(savedAdresse);

        // Act
        Adresse result = adresseService.saveAdresse(adresse);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(adresseRepository, times(1)).save(adresse);
    }

    @Test
    void testDeleteAdresse() {
        // Arrange
        Long id = 1L;
        doNothing().when(adresseRepository).deleteById(id);

        // Act
        adresseService.deleteAdresse(id);

        // Assert
        verify(adresseRepository, times(1)).deleteById(id);
    }
}

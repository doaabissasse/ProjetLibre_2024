package com.example.dossiers_service;

import com.example.dossiers_service.Entite.Adresse;
import com.example.dossiers_service.service.AdresseService;
import com.example.dossiers_service.repository.AdresseRepository;
import com.example.dossiers_service.Client.ContactClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdresseServiceTest {

    private AdresseService adresseService;
    private AdresseRepository adresseRepository;
    private ContactClient contactClient;

    @BeforeEach
    void setUp() {
        adresseRepository = Mockito.mock(AdresseRepository.class);
        contactClient = Mockito.mock(ContactClient.class);
        adresseService = new AdresseService(adresseRepository, contactClient);
    }

    @Test
    void testCreateAdresse() {
        Adresse adresse = new Adresse(1L, "123", "Main Street", "75001", "Paris", "Paris");
        when(adresseRepository.save(adresse)).thenReturn(adresse);

        Adresse result = adresseService.createAdresse(adresse);
        assertEquals(adresse, result);
    }

    @Test
    void testGetAllAdresses() {
        List<Adresse> adresses = Arrays.asList(
                new Adresse(1L, "123", "Main Street", "75001", "Paris", "Paris"),
                new Adresse(2L, "456", "Broadway", "10001", "New York", "NY")
        );
        when(adresseRepository.findAll()).thenReturn(adresses);

        List<Adresse> result = adresseService.getAllAdresses();
        assertEquals(2, result.size());
        assertEquals(adresses, result);
    }

    @Test
    void testGetAdresseById() {
        Adresse adresse = new Adresse(1L, "123", "Main Street", "75001", "Paris", "Paris");
        when(adresseRepository.findById(1L)).thenReturn(Optional.of(adresse));

        Optional<Adresse> result = adresseService.getAdresseById(1L);
        assertTrue(result.isPresent());
        assertEquals(adresse, result.get());
    }

    @Test
    void testGetAdresseById_NotFound() {
        when(adresseRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Adresse> result = adresseService.getAdresseById(1L);
        assertFalse(result.isPresent());
    }

    @Test
    void testGetAdressesByVille() {
        List<Adresse> adresses = Arrays.asList(
                new Adresse(1L, "123", "Main Street", "75001", "Paris", "Paris"),
                new Adresse(2L, "456", "Boulevard", "75001", "Paris", "Paris")
        );
        when(adresseRepository.findByVille("Paris")).thenReturn(adresses);

        List<Adresse> result = adresseService.getAdressesByVille("Paris");
        assertEquals(2, result.size());
        assertEquals(adresses, result);
    }

    @Test
    void testDeleteAdresse() {
        doNothing().when(adresseRepository).deleteById(1L);

        adresseService.deleteAdresse(1L);
        verify(adresseRepository, times(1)).deleteById(1L);
    }


}

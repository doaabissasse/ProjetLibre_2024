package com.example.contactes_service;

import com.example.contactes_service.Entite.contacte;
import com.example.contactes_service.service.contacteService;
import com.example.contactes_service.repository.ContacteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContacteServiceTest {

    @InjectMocks
    private contacteService contacteService; // The service under test

    @Mock
    private ContacteRepository contacteRepository; // Mocked dependency

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void save_ShouldReturnSavedContacte() {
        // Arrange
        contacte mockContacte = contacte.builder()
                .idLaboratoire(1L)
                .tel("123456789")
                .fax("987654321")
                .email("test@example.com")
                .build();

        when(contacteRepository.save(mockContacte)).thenReturn(mockContacte);

        // Act
        contacte result = contacteService.save(mockContacte);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getIdLaboratoire());
        assertEquals("123456789", result.getTel());
        assertEquals("987654321", result.getFax());
        assertEquals("test@example.com", result.getEmail());
        verify(contacteRepository, times(1)).save(mockContacte); // Verify save was called once
    }

    @Test
    void findAll_ShouldReturnListOfContactes() {
        // Arrange
        contacte contacte1 = contacte.builder()
                .id(1L)
                .idLaboratoire(1L)
                .tel("123456789")
                .fax("987654321")
                .email("contact1@example.com")
                .build();

        contacte contacte2 = contacte.builder()
                .id(2L)
                .idLaboratoire(2L)
                .tel("987654321")
                .fax("123456789")
                .email("contact2@example.com")
                .build();

        when(contacteRepository.findAll()).thenReturn(Arrays.asList(contacte1, contacte2));

        // Act
        List<contacte> result = contacteService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        // Validate first contact
        assertEquals(1L, result.get(0).getId());
        assertEquals("contact1@example.com", result.get(0).getEmail());

        // Validate second contact
        assertEquals(2L, result.get(1).getId());
        assertEquals("contact2@example.com", result.get(1).getEmail());

        verify(contacteRepository, times(1)).findAll(); // Verify findAll was called once
    }

    @Test
    void findAlllbyaboratoire_ShouldReturnListOfContactesForGivenLabId() {
        // Arrange
        long labId = 1L;
        contacte contacte1 = contacte.builder()
                .id(1L)
                .idLaboratoire(labId)
                .tel("123456789")
                .fax("987654321")
                .email("lab1contact@example.com")
                .build();

        when(contacteRepository.findAllByIdLaboratoire(labId)).thenReturn(Arrays.asList(contacte1));

        // Act
        List<contacte> result = contacteService.findAlllbyaboratoire(labId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(labId, result.get(0).getIdLaboratoire());
        assertEquals("lab1contact@example.com", result.get(0).getEmail());

        verify(contacteRepository, times(1)).findAllByIdLaboratoire(labId); // Verify the repository method was called
    }
}

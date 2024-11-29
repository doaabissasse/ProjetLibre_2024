package com.example.labo_service;

import com.example.labo_service.Client.ContactClient;
import com.example.labo_service.Client.UserClient;
import com.example.labo_service.Entite.*;
import com.example.labo_service.repository.LaboratoireRepository;
import com.example.labo_service.service.LaboratoireService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LaboratoireServiceTest {

    @Mock
    private LaboratoireRepository laboratoireRepository;

    @Mock
    private ContactClient contactClient;

    @Mock
    private UserClient userClient;

    @InjectMocks
    private LaboratoireService laboratoireService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveLaboratoire() {
        // Arrange
        Laboratoire labo = Laboratoire.builder()
                .id(1L)
                .nom("Labo Test")
                .logo("logo.png")
                .nrc("12345")
                .active(true)
                .dateActivation(LocalDate.now())
                .build();

        when(laboratoireRepository.save(labo)).thenReturn(labo);

        // Act
        Laboratoire savedLabo = laboratoireService.save(labo);

        // Assert
        assertNotNull(savedLabo);
        assertEquals("Labo Test", savedLabo.getNom());
        verify(laboratoireRepository, times(1)).save(labo);
    }

    @Test
    void testFindAllLaboratoires() {
        // Arrange
        Laboratoire labo1 = Laboratoire.builder().id(1L).nom("Labo 1").build();
        Laboratoire labo2 = Laboratoire.builder().id(2L).nom("Labo 2").build();

        when(laboratoireRepository.findAll()).thenReturn(Arrays.asList(labo1, labo2));

        // Act
        List<Laboratoire> laboratoires = laboratoireService.findAll();

        // Assert
        assertEquals(2, laboratoires.size());
        verify(laboratoireRepository, times(1)).findAll();
    }

    @Test
    void testFindLabowithContactes() {
        // Arrange
        long laboId = 1L;
        Laboratoire labo = Laboratoire.builder()
                .id(laboId)
                .nom("Labo 1")
                .logo("logo1.png")
                .nrc("123")
                .active(true)
                .dateActivation(LocalDate.now())
                .build();

        List<contacte> contactes = Arrays.asList(
                new contacte("123456789", "fax1", "email1@example.com"),
                new contacte("987654321", "fax2", "email2@example.com")
        );

        when(laboratoireRepository.findById(laboId)).thenReturn(Optional.of(labo));
        when(contactClient.findAllContratbyLabo(laboId)).thenReturn(contactes);

        // Act
        FullLaboratoireResponse response = laboratoireService.findLabowithContactes(laboId);

        // Assert
        assertNotNull(response);
        assertEquals("Labo 1", response.getNom());
        assertEquals(2, response.getContactes().size());
        verify(laboratoireRepository, times(1)).findById(laboId);
        verify(contactClient, times(1)).findAllContratbyLabo(laboId);
    }

    @Test
    void testFindLabowithUsers() {
        // Arrange
        long laboId = 2L;
        Laboratoire labo = Laboratoire.builder()
                .id(laboId)
                .nom("Labo 2")
                .logo("logo2.png")
                .nrc("456")
                .active(true)
                .dateActivation(LocalDate.now())
                .build();

        List<user> users = Arrays.asList(
                new user("user1@example.com", "User One", "Engineer", "123456789", "signature1", "Admin"),
                new user("user2@example.com", "User Two", "Technician", "987654321", "signature2", "User")
        );

        when(laboratoireRepository.findById(laboId)).thenReturn(Optional.of(labo));
        when(userClient.findAllUSERSbyLabo(laboId)).thenReturn(users);

        // Act
        FullLaboratoirewithUSER response = laboratoireService.findLabowithUsers(laboId);

        // Assert
        assertNotNull(response);
        assertEquals("Labo 2", response.getNom());
        assertEquals(2, response.getUsers().size());
        verify(laboratoireRepository, times(1)).findById(laboId);
        verify(userClient, times(1)).findAllUSERSbyLabo(laboId);
    }
}


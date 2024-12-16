package com.example.patient_service;

import com.example.patient_service.entity.Patient;
import com.example.patient_service.repository.PatientRepository;
import com.example.patient_service.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPatients_ShouldReturnAllPatients() {
        // Arrange
        when(patientRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Patient(1L, "John", "Doe", LocalDate.of(1985, 1, 1), "New York", "Male", "123 Street", "johndoe@gmail.com", "123456789", "Passport", "123456789", "Public"),
                        new Patient(2L, "Jane", "Doe", LocalDate.of(1990, 2, 2), "Los Angeles", "Female", "456 Avenue", "janedoe@gmail.com", "987654321", "ID Card", "987654321", "Private")
                )
        );

        // Act
        var patients = patientService.getAllPatients();

        // Assert
        assertEquals(2, patients.size());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void getPatientById_ShouldReturnPatientIfFound() {
        // Arrange
        Long id = 1L;
        Patient patient = new Patient(1L, "John", "Doe", LocalDate.of(1985, 1, 1), "New York", "Male", "123 Street", "johndoe@gmail.com", "123456789", "Passport", "123456789", "Public");
        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));

        // Act
        Patient foundPatient = patientService.getPatientById(id);

        // Assert
        assertNotNull(foundPatient);
        assertEquals("John", foundPatient.getNom());
        verify(patientRepository, times(1)).findById(id);
    }

    @Test
    void createPatient_ShouldSaveAndReturnPatient() {
        // Arrange
        Patient patient = new Patient(null, "John", "Doe", LocalDate.of(1985, 1, 1), "New York", "Male", "123 Street", "johndoe@gmail.com", "123456789", "Passport", "123456789", "Public");
        when(patientRepository.save(patient)).thenReturn(new Patient(1L, "John", "Doe", LocalDate.of(1985, 1, 1), "New York", "Male", "123 Street", "johndoe@gmail.com", "123456789", "Passport", "123456789", "Public"));

        // Act
        Patient savedPatient = patientService.createPatient(patient);

        // Assert
        assertNotNull(savedPatient.getId());
        assertEquals("John", savedPatient.getNom());
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    void updatePatient_ShouldUpdatePatientIfExists() {
        // Arrange
        Long id = 1L;
        Patient existingPatient = new Patient(1L, "John", "Doe", LocalDate.of(1985, 1, 1), "New York", "Male", "123 Street", "johndoe@gmail.com", "123456789", "Passport", "123456789", "Public");
        Patient updatedDetails = new Patient(1L, "Jane", "Doe", LocalDate.of(1990, 2, 2), "Los Angeles", "Female", "456 Avenue", "janedoe@gmail.com", "987654321", "ID Card", "987654321", "Private");

        when(patientRepository.findById(id)).thenReturn(Optional.of(existingPatient));
        when(patientRepository.save(any(Patient.class))).thenReturn(new Patient(1L, "Jane", "Doe", LocalDate.of(1990, 2, 2), "Los Angeles", "Female", "456 Avenue", "janedoe@gmail.com", "987654321", "ID Card", "987654321", "Private"));

        // Act
        Patient updatedPatient = patientService.updatePatient(id, updatedDetails);

        // Assert
        assertNotNull(updatedPatient);
        assertEquals("Jane", updatedPatient.getNom());
        assertEquals("Los Angeles", updatedPatient.getLieuDeNaissance());
        verify(patientRepository, times(1)).findById(id);
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

}

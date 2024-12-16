package com.example.patient_service;

import com.example.patient_service.controller.PatientController;
import com.example.patient_service.entity.Patient;
import com.example.patient_service.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientControllerTest {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPatientById_ShouldReturnPatientIfFound() {
        // Arrange
        Long id = 1L;
        Patient patient = new Patient(1L, "John", "Doe", LocalDate.of(1985, 1, 1), "New York", "Male", "123 Street", "johndoe@gmail.com", "123456789", "Passport", "123456789", "Public");
        when(patientService.getPatientById(id)).thenReturn(patient);

        // Act
        ResponseEntity<Patient> response = patientController.getPatientById(id);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John", response.getBody().getNom());
        verify(patientService, times(1)).getPatientById(id);
    }

    @Test
    void createPatient_ShouldReturnCreatedPatient() {
        // Arrange
        Patient patient = new Patient(null, "John", "Doe", LocalDate.of(1985, 1, 1), "New York", "Male", "123 Street", "johndoe@gmail.com", "123456789", "Passport", "123456789", "Public");
        when(patientService.createPatient(patient)).thenReturn(new Patient(1L, "John", "Doe", LocalDate.of(1985, 1, 1), "New York", "Male", "123 Street", "johndoe@gmail.com", "123456789", "Passport", "123456789", "Public"));

        // Act
        ResponseEntity<Patient> response = patientController.createPatient(patient);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John", response.getBody().getNom());
        verify(patientService, times(1)).createPatient(patient);
    }

    @Test
    void updatePatient_ShouldReturnUpdatedPatient() {
        // Arrange
        Long id = 1L;
        Patient existingPatient = new Patient(1L, "John", "Doe", LocalDate.of(1985, 1, 1), "New York", "Male", "123 Street", "johndoe@gmail.com", "123456789", "Passport", "123456789", "Public");
        Patient updatedDetails = new Patient(1L, "Jane", "Doe", LocalDate.of(1990, 2, 2), "Los Angeles", "Female", "456 Avenue", "janedoe@gmail.com", "987654321", "ID Card", "987654321", "Private");

        when(patientService.getPatientById(id)).thenReturn(existingPatient);
        when(patientService.updatePatient(id, updatedDetails)).thenReturn(new Patient(1L, "Jane", "Doe", LocalDate.of(1990, 2, 2), "Los Angeles", "Female", "456 Avenue", "janedoe@gmail.com", "987654321", "ID Card", "987654321", "Private"));

        // Act
        ResponseEntity<Patient> response = patientController.updatePatient(id, updatedDetails);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Jane", response.getBody().getNom());
        verify(patientService, times(1)).updatePatient(id, updatedDetails);
    }

}

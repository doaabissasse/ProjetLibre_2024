package com.example.test_analyse_service;

import com.example.test_analyse_service.controller.TestAnalyseController;
import com.example.test_analyse_service.entity.TestAnalyse;
import com.example.test_analyse_service.service.TestAnalyseService;
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

class TestAnalyseControllerTest {

    @Mock
    private TestAnalyseService testAnalyseService;

    @InjectMocks
    private TestAnalyseController testAnalyseController;

    private TestAnalyse testAnalyse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testAnalyse = new TestAnalyse(1L, 101L, "TestNom", "SousEpreuve", 10.00, 100.00, "Unit", "Details");
    }

    @Test
    void createTestAnalyse_ShouldReturnCreatedTestAnalyse() {
        when(testAnalyseService.saveTestAnalyse(testAnalyse)).thenReturn(testAnalyse);

        ResponseEntity<TestAnalyse> response = testAnalyseController.createTestAnalyse(testAnalyse);

        assertNotNull(response);
        assertEquals(testAnalyse, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(testAnalyseService, times(1)).saveTestAnalyse(testAnalyse);
    }

    @Test
    void getTestAnalyseById_ShouldReturnTestAnalyseIfFound() {
        when(testAnalyseService.getTestAnalyseById(1L)).thenReturn(Optional.of(testAnalyse));

        ResponseEntity<TestAnalyse> response = testAnalyseController.getTestAnalyseById(1L);

        assertNotNull(response);
        assertEquals(testAnalyse, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(testAnalyseService, times(1)).getTestAnalyseById(1L);
    }

    @Test
    void getTestAnalyseById_NotFound_ShouldReturnNotFound() {
        when(testAnalyseService.getTestAnalyseById(1L)).thenReturn(Optional.empty());

        ResponseEntity<TestAnalyse> response = testAnalyseController.getTestAnalyseById(1L);

        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        verify(testAnalyseService, times(1)).getTestAnalyseById(1L);
    }

    @Test
    void getAllTestAnalyses_ShouldReturnAllTestAnalyses() {
        List<TestAnalyse> testAnalyses = Arrays.asList(testAnalyse);
        when(testAnalyseService.getAllTestAnalyses()).thenReturn(testAnalyses);

        ResponseEntity<List<TestAnalyse>> response = testAnalyseController.getAllTestAnalyses();

        assertNotNull(response);
        assertEquals(testAnalyses, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(testAnalyseService, times(1)).getAllTestAnalyses();
    }
//update
    @Test
    void updateTestAnalyse_ShouldReturnUpdatedTestAnalyse() {
        when(testAnalyseService.updateTestAnalyse(1L, testAnalyse)).thenReturn(testAnalyse);

        ResponseEntity<TestAnalyse> response = testAnalyseController.updateTestAnalyse(1L, testAnalyse);

        assertNotNull(response);
        assertEquals(testAnalyse, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(testAnalyseService, times(1)).updateTestAnalyse(1L, testAnalyse);
    }

    @Test
    void deleteTestAnalyse_ShouldReturnNoContent() {
        doNothing().when(testAnalyseService).deleteTestAnalyse(1L);

        ResponseEntity<Void> response = testAnalyseController.deleteTestAnalyse(1L);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(testAnalyseService, times(1)).deleteTestAnalyse(1L);
    }
}

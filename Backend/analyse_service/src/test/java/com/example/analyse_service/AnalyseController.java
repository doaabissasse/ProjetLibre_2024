package com.example.analyse_service;

import com.example.analyse_service.controller.AnalyseController;
import com.example.analyse_service.entity.Analyse;
import com.example.analyse_service.service.AnalyseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnalyseControllerTest {

    @Mock
    private AnalyseService analyseService;

    @InjectMocks
    private AnalyseController analyseController;

    public AnalyseControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAnalyses() {
        List<Analyse> mockAnalyses = Arrays.asList(
                new Analyse(1L, 100L, "Analyse 1", "Description 1"),
                new Analyse(2L, 101L, "Analyse 2", "Description 2")
        );

        when(analyseService.getAllAnalyses()).thenReturn(mockAnalyses);

        List<Analyse> analyses = analyseController.getAllAnalyses();

        assertEquals(2, analyses.size());
        verify(analyseService, times(1)).getAllAnalyses();
    }

    @Test
    void testGetAnalyseById() {
        Analyse mockAnalyse = new Analyse(1L, 100L, "Analyse 1", "Description 1");

        when(analyseService.getAnalyseById(1L)).thenReturn(mockAnalyse);

        ResponseEntity<Analyse> response = analyseController.getAnalyseById(1L);

        assertNotNull(response.getBody());
        assertEquals("Analyse 1", response.getBody().getNom());
        verify(analyseService, times(1)).getAnalyseById(1L);
    }

    @Test
    void testCreateAnalyse() {
        Analyse mockAnalyse = new Analyse(null, 100L, "Analyse 1", "Description 1");
        Analyse savedAnalyse = new Analyse(1L, 100L, "Analyse 1", "Description 1");

        when(analyseService.createAnalyse(mockAnalyse)).thenReturn(savedAnalyse);

        ResponseEntity<Analyse> response = analyseController.createAnalyse(mockAnalyse);

        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        verify(analyseService, times(1)).createAnalyse(mockAnalyse);
    }

    @Test
    void testUpdateAnalyse() {
        Analyse updatedAnalyse = new Analyse(1L, 101L, "Updated Analyse", "Updated Description");

        when(analyseService.updateAnalyse(1L, updatedAnalyse)).thenReturn(updatedAnalyse);

        ResponseEntity<Analyse> response = analyseController.updateAnalyse(1L, updatedAnalyse);

        assertNotNull(response.getBody());
        assertEquals("Updated Analyse", response.getBody().getNom());
        verify(analyseService, times(1)).updateAnalyse(1L, updatedAnalyse);
    }

    @Test
    void testDeleteAnalyse() {
        doNothing().when(analyseService).deleteAnalyse(1L);

        ResponseEntity<Void> response = analyseController.deleteAnalyse(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(analyseService, times(1)).deleteAnalyse(1L);
    }
}

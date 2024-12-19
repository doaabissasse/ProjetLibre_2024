package com.example.analyse_service;

import com.example.analyse_service.entity.Analyse;
import com.example.analyse_service.repository.AnalyseRepository;
import com.example.analyse_service.service.AnalyseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnalyseServiceTest {

    @Mock
    private AnalyseRepository analyseRepository;

    @InjectMocks
    private AnalyseService analyseService;

    public AnalyseServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAnalyses() {
        // Mock data
        List<Analyse> mockAnalyses = Arrays.asList(
                new Analyse(1L, 100L, "Analyse 1", "Description 1"),
                new Analyse(2L, 101L, "Analyse 2", "Description 2")
        );

        when(analyseRepository.findAll()).thenReturn(mockAnalyses);

        // Test
        List<Analyse> analyses = analyseService.getAllAnalyses();

        assertEquals(2, analyses.size());
        verify(analyseRepository, times(1)).findAll();
    }

    @Test
    void testGetAnalyseById() {
        Analyse mockAnalyse = new Analyse(1L, 100L, "Analyse 1", "Description 1");

        when(analyseRepository.findById(1L)).thenReturn(Optional.of(mockAnalyse));

        Analyse analyse = analyseService.getAnalyseById(1L);

        assertNotNull(analyse);
        assertEquals("Analyse 1", analyse.getNom());
        verify(analyseRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateAnalyse() {
        Analyse mockAnalyse = new Analyse(null, 100L, "Analyse 1", "Description 1");
        Analyse savedAnalyse = new Analyse(1L, 100L, "Analyse 1", "Description 1");

        when(analyseRepository.save(mockAnalyse)).thenReturn(savedAnalyse);

        Analyse analyse = analyseService.createAnalyse(mockAnalyse);

        assertNotNull(analyse);
        assertEquals(1L, analyse.getId());
        verify(analyseRepository, times(1)).save(mockAnalyse);
    }

    @Test
    void testUpdateAnalyse() {
        Analyse existingAnalyse = new Analyse(1L, 100L, "Analyse 1", "Description 1");
        Analyse updatedAnalyse = new Analyse(1L, 101L, "Updated Analyse", "Updated Description");

        when(analyseRepository.findById(1L)).thenReturn(Optional.of(existingAnalyse));
        when(analyseRepository.save(any(Analyse.class))).thenReturn(updatedAnalyse);

        Analyse result = analyseService.updateAnalyse(1L, updatedAnalyse);

        assertNotNull(result);
        assertEquals("Updated Analyse", result.getNom());
        verify(analyseRepository, times(1)).findById(1L);
        verify(analyseRepository, times(1)).save(existingAnalyse);
    }

    @Test
    void testDeleteAnalyse() {
        Long analyseId = 1L;

        doNothing().when(analyseRepository).deleteById(analyseId);

        analyseService.deleteAnalyse(analyseId);

        verify(analyseRepository, times(1)).deleteById(analyseId);
    }
}

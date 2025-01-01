package com.example.analyse_service;

import com.example.analyse_service.controller.AnalyseController;
import com.example.analyse_service.entity.Analyse;
import com.example.analyse_service.service.AnalyseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AnalyseControllerTest {

    @InjectMocks
    private AnalyseController analyseController;

    @Mock
    private AnalyseService analyseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAnalyses() {
        Analyse analyse1 = new Analyse(1L, 101L, "Test Analyse 1", "Description 1");
        Analyse analyse2 = new Analyse(2L, 102L, "Test Analyse 2", "Description 2");

        when(analyseService.getAllAnalyses()).thenReturn(Arrays.asList(analyse1, analyse2));

        List<Analyse> analyses = analyseController.getAllAnalyses();

        assertThat(analyses).hasSize(2);
        assertThat(analyses.get(0).getNom()).isEqualTo("Test Analyse 1");
        assertThat(analyses.get(1).getNom()).isEqualTo("Test Analyse 2");
    }

    @Test
    public void testGetAnalyseById() {
        Analyse analyse = new Analyse(1L, 101L, "Test Analyse", "Description");

        when(analyseService.getAnalyseById(1L)).thenReturn(analyse);

        ResponseEntity<Analyse> response = analyseController.getAnalyseById(1L);

        assertThat(response.getBody()).isEqualTo(analyse);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }

    @Test
    public void testCreateAnalyse() {
        Analyse analyse = new Analyse(1L, 101L, "New Analyse", "New Description");

        when(analyseService.save(any(Analyse.class))).thenReturn(analyse);

        Analyse createdAnalyse = analyseController.createAnalyse(analyse);

        assertThat(createdAnalyse).isEqualTo(analyse);
        assertThat(createdAnalyse.getId()).isEqualTo(1L);
    }

    @Test
    public void testUpdateAnalyse() {
        Analyse existingAnalyse = new Analyse(1L, 101L, "Existing Analyse", "Existing Description");
        Analyse updatedAnalyse = new Analyse(1L, 101L, "Updated Analyse", "Updated Description");

        when(analyseService.updateAnalyse(1L, updatedAnalyse)).thenReturn(updatedAnalyse);

        ResponseEntity<Analyse> response = analyseController.updateAnalyse(1L, updatedAnalyse);

        assertThat(response.getBody()).isEqualTo(updatedAnalyse);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }

    @Test
    public void testDeleteAnalyse() {
        doNothing().when(analyseService).deleteAnalyse(1L);

        ResponseEntity<Void> response = analyseController.deleteAnalyse(1L);

        verify(analyseService, times(1)).deleteAnalyse(1L);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }

}
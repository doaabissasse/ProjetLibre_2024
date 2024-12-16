package com.example.test_analyse_service;

import com.example.test_analyse_service.entity.TestAnalyse;
import com.example.test_analyse_service.repository.TestAnalyseRepository;
import com.example.test_analyse_service.service.TestAnalyseService;
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

class TestAnalyseServiceTest {

    @Mock
    private TestAnalyseRepository testAnalyseRepository;

    @InjectMocks
    private TestAnalyseService testAnalyseService;

    private TestAnalyse testAnalyse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testAnalyse = new TestAnalyse(1L, 101L, "TestNom", "SousEpreuve", 10.00, 100.00, "Unit", "Details");
    }

    @Test
    void createTestAnalyse_ShouldReturnSavedTestAnalyse() {
        when(testAnalyseRepository.save(testAnalyse)).thenReturn(testAnalyse);

        TestAnalyse createdTestAnalyse = testAnalyseService.saveTestAnalyse(testAnalyse);

        assertNotNull(createdTestAnalyse);
        assertEquals(testAnalyse, createdTestAnalyse);
        verify(testAnalyseRepository, times(1)).save(testAnalyse);
    }

    @Test
    void getTestAnalyseById_ShouldReturnTestAnalyseIfFound() {
        when(testAnalyseRepository.findById(1L)).thenReturn(Optional.of(testAnalyse));

        Optional<TestAnalyse> foundTestAnalyse = testAnalyseService.getTestAnalyseById(1L);

        assertTrue(foundTestAnalyse.isPresent());
        assertEquals(testAnalyse, foundTestAnalyse.get());
        verify(testAnalyseRepository, times(1)).findById(1L);
    }

    @Test
    void getAllTestAnalyses_ShouldReturnAllTestAnalyses() {
        List<TestAnalyse> testAnalyses = Arrays.asList(testAnalyse);
        when(testAnalyseRepository.findAll()).thenReturn(testAnalyses);

        List<TestAnalyse> result = testAnalyseService.getAllTestAnalyses();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testAnalyse, result.get(0));
        verify(testAnalyseRepository, times(1)).findAll();
    }

    @Test
    void getTestAnalysesByIdAnalyse_ShouldReturnTestAnalysesForIdAnalyse() {
        List<TestAnalyse> testAnalyses = Arrays.asList(testAnalyse);
        when(testAnalyseRepository.findByIdAnalyse(101L)).thenReturn(testAnalyses);

        List<TestAnalyse> result = testAnalyseService.getTestAnalysesByIdAnalyse(101L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testAnalyse, result.get(0));
        verify(testAnalyseRepository, times(1)).findByIdAnalyse(101L);
    }

    @Test
    void updateTestAnalyse_ShouldReturnUpdatedTestAnalyse() {
        when(testAnalyseRepository.findById(1L)).thenReturn(Optional.of(testAnalyse));
        when(testAnalyseRepository.save(testAnalyse)).thenReturn(testAnalyse);

        TestAnalyse updatedTestAnalyse = testAnalyseService.updateTestAnalyse(1L, testAnalyse);

        assertNotNull(updatedTestAnalyse);
        assertEquals("TestNom", updatedTestAnalyse.getNomTest());
        verify(testAnalyseRepository, times(1)).findById(1L);
        verify(testAnalyseRepository, times(1)).save(testAnalyse);
    }

    @Test
    void deleteTestAnalyse_ShouldCallRepositoryDelete() {
        when(testAnalyseRepository.findById(1L)).thenReturn(Optional.of(testAnalyse));

        testAnalyseService.deleteTestAnalyse(1L);

        verify(testAnalyseRepository, times(1)).deleteById(1L);
    }
}

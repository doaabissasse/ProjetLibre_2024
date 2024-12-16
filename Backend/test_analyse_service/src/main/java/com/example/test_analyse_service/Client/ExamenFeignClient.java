package com.example.test_analyse_service.Client;

import com.example.test_analyse_service.entity.ExamenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "examen-service", url = "http://localhost:8091/api/examens")
public interface ExamenFeignClient {

    @GetMapping("/by-test-analyse/{idTestAnalyse}")
    List<ExamenDTO> getExamensByIdTestAnalyse(@PathVariable Long idTestAnalyse);
}
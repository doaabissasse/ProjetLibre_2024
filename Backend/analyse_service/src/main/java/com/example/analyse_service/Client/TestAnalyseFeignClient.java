package com.example.analyse_service.Client;

import com.example.analyse_service.entity.TestAnalyse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "test-analyse-service", url = "http://localhost:8092/api/test-analyses") // Update with actual service name and URL
public interface TestAnalyseFeignClient {

    @GetMapping("/by-analyse/{idAnalyse}")
    List<TestAnalyse> getTestsByAnalyseId(@PathVariable("idAnalyse") Long idAnalyse);
}

package com.example.analyse_service.Client;

import com.example.analyse_service.entity.epreuve;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "epreuve-service", url = "http://localhost:8094/api/epreuves") // Update with actual service name and URL
public interface EpreuveFeignClient {

    @GetMapping("/by-analyse/{idAnalyse}")
    List<epreuve> getEpreuvesByAnalyseId(@PathVariable("idAnalyse") Long idAnalyse);
}
package com.example.labo_service.Client;

import com.example.labo_service.Entite.Analyse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "analyse-service", url = "http://localhost:8087/api/analyses")
public interface AnalyseFeignClient {

    @GetMapping("/by-laboratoire/{idLabo}")
    List<Analyse> getAnalysesByLaboratoireId(@PathVariable("idLabo") Long idLabo);
}

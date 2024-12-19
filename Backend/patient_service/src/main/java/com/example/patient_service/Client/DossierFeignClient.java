package com.example.patient_service.Client;

import com.example.patient_service.entity.DossierDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "dossiersService", url = "http://localhost:8093/api/dossiers")
public interface DossierFeignClient {

    @GetMapping("/patient/{idPatient}")
    List<DossierDto> getDossiersByPatientId(@PathVariable("idPatient") Long idPatient);

}
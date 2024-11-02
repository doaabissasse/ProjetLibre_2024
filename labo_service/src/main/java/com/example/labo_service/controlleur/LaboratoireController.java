package com.example.labo_service.controlleur;

import com.example.labo_service.Entite.FullLaboratoireResponse;
import com.example.labo_service.Entite.Laboratoire;
import com.example.labo_service.service.LaboratoireService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/laboratoires")
public class  LaboratoireController {

    private final LaboratoireService laboratoireService; // Injecté via le constructeur

    @PostMapping
    public Laboratoire addLaboratoire(@RequestBody Laboratoire laboratoire) {
        return laboratoireService.save(laboratoire);
    }

    @GetMapping
    public List<Laboratoire> listLaboratoires() {
        return laboratoireService.findAll();
    }

    @GetMapping("/contactes/{labo_id}")
    public FullLaboratoireResponse listLaboratoireContactes(@PathVariable("labo_id") long idLaboratoire) {
        return laboratoireService.findLabowithContactes(idLaboratoire);
    }
}

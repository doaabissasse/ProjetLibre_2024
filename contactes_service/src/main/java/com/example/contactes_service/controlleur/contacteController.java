package com.example.contactes_service.controlleur;

import com.example.contactes_service.Entite.contacte;
import com.example.contactes_service.service.contacteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/contactes")
public class contacteController {

    @Autowired
    private contacteService contacteService;

    @PostMapping
    public contacte addcontacte(@RequestBody contacte contacte)
    {
        return contacteService.save(contacte);
    }

    @GetMapping
    public List<contacte> listcontactes()
    {
        return contacteService.findAll();
    }

    @GetMapping("/laboratoire/{labo_id}")
    public List<contacte> listcontactes(
            @PathVariable("labo_id") long laboID
    )
    {
        return contacteService.findAlllaboratoirebyContact(laboID);
    }

}
package com.example.contactes_service.service;

import com.example.contactes_service.Entite.contacte;
import com.example.contactes_service.repository.ContacteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class contacteService {

    @Autowired
    private ContacteRepository contacteRepository;


    public contacte save(contacte contacte)
    {
        return contacteRepository.save(contacte);
    }

    public List<contacte> findAll()
    {
        return contacteRepository.findAll();
    }

    public List<contacte> findAlllbyaboratoire(long laboId) {
        return contacteRepository.findAllByIdLaboratoire(laboId);
    }
}


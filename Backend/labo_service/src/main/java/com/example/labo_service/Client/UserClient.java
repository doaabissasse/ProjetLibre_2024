package com.example.labo_service.Client;

import com.example.labo_service.Entite.user;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name= "user-service", url="${application.config.user-url}")
public interface UserClient {
    @GetMapping("/laboratoire/{labo_id}")
    List<user> findAllUSERSbyLabo(@PathVariable("labo_id") long laboID);
}

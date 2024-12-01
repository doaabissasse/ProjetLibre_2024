package com.example.users_service.repositories;

import com.example.users_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllUtilisateursByFkIdLaboratoire(long fkIdLaboratoire);
    Optional<User> findByEmail(String email);

}

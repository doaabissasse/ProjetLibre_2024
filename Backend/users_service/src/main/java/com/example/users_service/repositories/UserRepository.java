package com.example.users_service.repositories;

import com.example.users_service.entities.User;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllUtilisateursByFkIdLaboratoire(long fkIdLaboratoire);
    Optional<User> findByEmail(String email);
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleName")
    List<User> findUsersByRole(@Param("roleName") String roleName);
}

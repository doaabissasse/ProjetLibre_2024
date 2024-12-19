package com.example.users_service.repositories;

import com.example.users_service.roles.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  RoleRepository extends JpaRepository<Role, Integer> {


    Optional<Role> findByName(String  role);
}

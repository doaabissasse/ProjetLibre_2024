package com.example.users_service.tockenRepositories;

import com.example.users_service.tockens.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TockenRepository extends JpaRepository<Token, Integer> {

    Optional<Token> findByToken(String tocken);
}

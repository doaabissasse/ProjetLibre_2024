package com.example.users_service.tockenRepositories;

import com.example.users_service.entities.User;
import com.example.users_service.tockens.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TockenRepository extends JpaRepository<Token, Integer> {

    Token findByToken(String tocken);
    List<Token> findByUser(User user);
}

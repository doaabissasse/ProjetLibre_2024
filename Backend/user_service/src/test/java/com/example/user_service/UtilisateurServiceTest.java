package com.example.user_service;

import com.example.user_service.Entities.Utilisateur;
import com.example.user_service.Repositories.UtilisateurRepository;
import com.example.user_service.Services.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UtilisateurServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UtilisateurService utilisateurService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUtilisateurs() {
        // Arrange
        Utilisateur user1 = Utilisateur.builder().id(1L).nomComplet("User One").build();
        Utilisateur user2 = Utilisateur.builder().id(2L).nomComplet("User Two").build();

        when(utilisateurRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        // Act
        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();

        // Assert
        assertNotNull(utilisateurs);
        assertEquals(2, utilisateurs.size());
        verify(utilisateurRepository, times(1)).findAll();
    }

    @Test
    void testGetUtilisateurById() {
        // Arrange
        long userId = 1L;
        Utilisateur user = Utilisateur.builder().id(userId).nomComplet("User One").build();

        when(utilisateurRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        Utilisateur foundUser = utilisateurService.getUtilisateurById(userId);

        // Assert
        assertNotNull(foundUser);
        assertEquals("User One", foundUser.getNomComplet());
        verify(utilisateurRepository, times(1)).findById(userId);
    }

    @Test
    void testSaveUser() {
        // Arrange
        Utilisateur user = Utilisateur.builder()
                .id(1L)
                .username("testuser")
                .password("plaintextpassword")
                .build();

        String encodedPassword = "encodedpassword";
        when(passwordEncoder.encode("plaintextpassword")).thenReturn(encodedPassword);
        when(utilisateurRepository.save(user)).thenReturn(user);

        // Act
        Utilisateur savedUser = utilisateurService.saveUser(user);

        // Assert
        assertNotNull(savedUser);
        assertEquals("encodedpassword", user.getPassword()); // Check that the password was encoded
        verify(passwordEncoder, times(1)).encode("plaintextpassword"); // Ensure encoding was called
        verify(utilisateurRepository, times(1)).save(user); // Ensure save was called
    }

    @Test
    void testGetAllUtilisateursByLabo() {
        // Arrange
        long laboId = 10L;
        Utilisateur user1 = Utilisateur.builder().id(1L).fkIdLaboratoire(laboId).build();
        Utilisateur user2 = Utilisateur.builder().id(2L).fkIdLaboratoire(laboId).build();

        when(utilisateurRepository.findAllUtilisateursByFkIdLaboratoire(laboId)).thenReturn(Arrays.asList(user1, user2));

        // Act
        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateursByLabo(laboId);

        // Assert
        assertNotNull(utilisateurs);
        assertEquals(2, utilisateurs.size());
        verify(utilisateurRepository, times(1)).findAllUtilisateursByFkIdLaboratoire(laboId);
    }

    @Test
    void testFindByUsername() {
        // Arrange
        String username = "testuser";
        Utilisateur user = Utilisateur.builder().id(1L).username(username).build();

        when(utilisateurRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Act
        Optional<Utilisateur> foundUser = utilisateurService.findByUsername(username);

        // Assert
        assertTrue(foundUser.isPresent());
        assertEquals("testuser", foundUser.get().getUsername());
        verify(utilisateurRepository, times(1)).findByUsername(username);
    }
}

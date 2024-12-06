package com.example.users_service;

import com.example.users_service.entities.User;
import com.example.users_service.repositories.UserRepository;
import com.example.users_service.services.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UsersServiceApplicationTests {
		@Mock
		private UserRepository utilisateurRepository;

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
			User user1 = User.builder().id(1).nomComplet("User One").build();
			User user2 = User.builder().id(2).nomComplet("User Two").build();

			when(utilisateurRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

			// Act
			List<User> utilisateurs = utilisateurService.getAllUtilisateurs();

			// Assert
			assertNotNull(utilisateurs);
			assertEquals(2, utilisateurs.size());
			verify(utilisateurRepository, times(1)).findAll();
		}


	@Test
		void testGetUtilisateurById() {
			// Arrange
			Integer userId = 1;
			User user = User.builder().id(userId).nomComplet("User One").build();

			when(utilisateurRepository.findById(userId)).thenReturn(Optional.of(user));

			// Act
			User foundUser = utilisateurService.getUtilisateurById(userId);

			// Assert
			assertNotNull(foundUser);
			assertEquals("User One", foundUser.getNomComplet());
			verify(utilisateurRepository, times(1)).findById(userId);
		}

		@Test
		void testSaveUser() {
			// Arrange
			User user = User.builder()
					.id(1)
					.email("testuser")
					.password("plaintextpassword")
					.build();

			String encodedPassword = "encodedpassword";
			when(passwordEncoder.encode("plaintextpassword")).thenReturn(encodedPassword);
			when(utilisateurRepository.save(user)).thenReturn(user);

			// Act
			User savedUser = utilisateurService.createUtilisateur(user);

			// Assert
			assertNotNull(savedUser);
			assertEquals("encodedpassword", user.getPassword()); // Check that the password was encoded
			verify(passwordEncoder, times(1)).encode("plaintextpassword"); // Ensure encoding was called
			verify(utilisateurRepository, times(1)).save(user); // Ensure save was called
		}

		@Test
		void testGetAllUtilisateursByLabo() {
			// Arrange
			Integer laboId = 10;
			User user1 = User.builder().id(1).fkIdLaboratoire(laboId).build();
			User user2 = User.builder().id(2).fkIdLaboratoire(laboId).build();

			when(utilisateurRepository.findAllUtilisateursByFkIdLaboratoire(laboId)).thenReturn(Arrays.asList(user1, user2));

			// Act
			List<User> utilisateurs = utilisateurService.getAllUtilisateursByLabo(laboId);

			// Assert
			assertNotNull(utilisateurs);
			assertEquals(2, utilisateurs.size());
			verify(utilisateurRepository, times(1)).findAllUtilisateursByFkIdLaboratoire(laboId);
		}

		@Test
		void testFindByUsername() {
			// Arrange
			String username = "Test";
			User user = User.builder().id(1).email(username).build();

			when(utilisateurRepository.findByEmail(username)).thenReturn(Optional.of(user));

			// Act
			Optional<User> foundUser = utilisateurService.findByUsername(username);

			// Assert
			assertTrue(foundUser.isPresent());
			assertEquals("testuser", foundUser.get().getEmail());
			verify(utilisateurRepository, times(1)).findByEmail(username);
		}
	}

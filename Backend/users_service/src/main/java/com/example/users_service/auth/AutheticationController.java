package com.example.users_service.auth;

import com.example.users_service.entities.User;
import com.example.users_service.repositories.UserRepository;
import com.example.users_service.services.UtilisateurService;
import com.example.users_service.tockenRepositories.TockenRepository;
import com.example.users_service.tockens.Token;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name="Authentication")
public class AutheticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    private final AuthenticationService service;

    private final UtilisateurService utilisateurService;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TockenRepository tockenRepository;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @RequestBody @Valid RegistrationRequest request
    ) throws MessagingException {
        try {
            service.register(request);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            // Log the exception
            System.out.println("Registration error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            System.out.println("Attempting to authenticate user: " + request.getEmail());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            User user = utilisateurService.findByEmail(request.getEmail());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("User not found"));
            }
            if (user.getRoles() != null && !user.getRoles().isEmpty()) {
                String role = user.getRoles().get(0).getName();
                String token = generateToken(user);
                // Retourner aussi l'utilisateur dans la réponse
                return ResponseEntity.ok(new AuthenticationResponse(token, role, user));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("User has no roles assigned"));
            }
        } catch (BadCredentialsException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Invalid email or password"));
        } catch (AuthenticationException e) {
            System.out.println("Authentication exception: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Authentication failed"));
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("An error occurred"));
        }
    }

    // Implémentez la méthode pour générer un token

    private String generateToken(User user) {

        // Logique pour générer un token (JWT, etc.)

        return "generated_token"; // Remplacez par votre logique de génération de token

    }



    @GetMapping("/activate-account")
    public void confirm(@RequestParam String token) throws MessagingException {
        Token savedToken = tockenRepository.findByToken(token);
        if (savedToken == null) {
            throw new IllegalArgumentException("Token invalide ou expiré.");
        }

        var user = userRepository.findById(savedToken.getUser().getId());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("Utilisateur introuvable pour ce token.");
        }

        user.get().setEnabled(true);
        userRepository.save(user.get());
        System.out.println("Utilisateur activé: " + user.get().getId());

        service.activateAccont(token);
    }
}

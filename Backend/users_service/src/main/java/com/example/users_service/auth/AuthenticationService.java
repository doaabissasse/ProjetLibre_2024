package com.example.users_service.auth;

import com.example.users_service.entities.User;
import com.example.users_service.repositories.RoleRepository;
import com.example.users_service.repositories.UserRepository;
import com.example.users_service.services.JwtService;
import com.example.users_service.tockenRepositories.TockenRepository;
import com.example.users_service.tockens.Token;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TockenRepository tockenRepository;
    private final EmailService emailService;
    private final JwtService jwtService;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    private final AuthenticationManager authenticationManager;

    public void register(RegistrationRequest request) throws MessagingException {
        var userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("User role not found"));

        var user = User.builder()
                .nomComplet(request.getNomComplet())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .profession(request.getProfession())
                .numTel(request.getNumTel())
                .fkIdLaboratoire(request.getFkIdLaboratoire())
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();

        userRepository.save(user);
        sendValidationEmail(user); // Correction du nom de la méthode
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);
        emailService.sendEmail(
                user.getEmail(),
                user.getNomComplet(),
                user.getPassword(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
               activationUrl,
                newToken,
                "Account activation"
        );
    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode(6);
        var token = Token.builder()
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .token(generatedToken) // Assurez-vous que le champ 'token' existe
                .build();
        tockenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }

    public AuthenticationResponse authenticate(@Valid AuthenticationRequest request) {
            var auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
          var claims = new HashMap<String, Object>();
          var user =((User)auth.getPrincipal());
          claims.put("nom complet", user.getNomComplet());
          var jwtToken = jwtService.generateToken(claims, (User) auth.getPrincipal());
          return  AuthenticationResponse.builder()
                  .token(jwtToken)
                  .build();
    }

    @Transactional
    public void activateAccont(String token) throws MessagingException {
        Token savedToken = tockenRepository.findByToken(token);
        if(LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation token has expired . A new token has been send to the same adresse ");
        }

        var user = userRepository.findById(savedToken.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setEnabled(true);

        userRepository.save(user);
        savedToken.setValidatedAt(LocalDateTime.now());
        tockenRepository.save(savedToken);
    }
}

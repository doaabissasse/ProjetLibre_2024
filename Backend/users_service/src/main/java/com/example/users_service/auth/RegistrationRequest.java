package com.example.users_service.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @Email(message = "Email is not formatted")
    @NotEmpty(message = "Email is mandatory")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotEmpty(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    private String nomComplet;

    @NotEmpty(message = "Profession is mandatory")
    @NotBlank(message = "Profession is mandatory")
    private String profession;

    @NotEmpty(message = "Phone number is mandatory")
    @NotBlank(message = "Phone number is mandatory")
    private String numTel;

    @NotEmpty(message = "Signature is mandatory")
    @NotBlank(message = "Signature is mandatory")
    private String signature;

    @Positive(message = "Laboratory ID must be positive")
    private long fkIdLaboratoire;

    @NotEmpty(message = "Password is mandatory")
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password should be at least 8 characters long")
    private String password;
}

package com.example.users_service.tockens;

import com.example.users_service.entities.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "token")
@EntityListeners(AuditingEntityListener.class)
public class Token {
    @Id
    @GeneratedValue
    private Integer id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime validatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

}

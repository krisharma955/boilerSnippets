package com.K955.boilerSnippets.Entity;

import com.K955.boilerSnippets.Enum.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    Long githubId;

    @Column(nullable = false, unique = true)
    String username;

    @Column
    String avatarUrl;

    @Column
    String profileUrl;

    @Builder.Default
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Role role = Role.USER;

    @CreationTimestamp
    Instant createdAt;

    @UpdateTimestamp
    Instant updatedAt;

}

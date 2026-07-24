package com.K955.boilerSnippets.Entity;

import com.K955.boilerSnippets.Enum.Snippet.Framework;
import com.K955.boilerSnippets.Enum.Snippet.Language;
import com.K955.boilerSnippets.Enum.Snippet.SnippetType;
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
@Table(name = "snippets")
public class Snippet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false, length = 1000)
    String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Language language;

    @Enumerated(EnumType.STRING)
    @Column
    Framework framework;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    SnippetType snippetType;

    @Column
    String frameworkVersion;

    @Column(nullable = false, columnDefinition = "TEXT")
    String code;

    @Column
    String filePath;

    @Column(length = 500)
    String dependencies; // comma-separated req dependencies

    @JoinColumn(name = "created_by")
    @ManyToOne(fetch = FetchType.LAZY)
    User createdBy; // admin who added it

    @CreationTimestamp
    Instant createdAt;

    @UpdateTimestamp
    Instant updatedAt;

}

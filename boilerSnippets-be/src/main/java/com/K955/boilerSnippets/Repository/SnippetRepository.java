package com.K955.boilerSnippets.Repository;

import com.K955.boilerSnippets.Entity.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnippetRepository extends JpaRepository<Snippet, Long> {
}

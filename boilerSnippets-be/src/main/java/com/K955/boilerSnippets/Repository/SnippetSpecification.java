package com.K955.boilerSnippets.Repository;

import com.K955.boilerSnippets.Entity.Snippet;
import com.K955.boilerSnippets.Enum.Snippet.Framework;
import com.K955.boilerSnippets.Enum.Snippet.Language;
import com.K955.boilerSnippets.Enum.Snippet.SnippetType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SnippetSpecification {

    public static Specification<Snippet> searchSnippet(
            Language language, Framework framework, SnippetType snippetType, String keyword
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(language != null) {
                predicates.add(cb.equal(root.get("language"), language));
            }

            if(framework != null) {
                predicates.add(cb.equal(root.get("framework"), framework));
            }

            if(snippetType != null) {
                predicates.add(cb.equal(root.get("snippetType"), snippetType));
            }

            if (keyword != null && !keyword.isBlank()) {
                String likePattern = "%" + keyword.toLowerCase() + "%";
                Predicate titleMatch = cb.like(cb.lower(root.get("keyword")), likePattern);
                Predicate descMatch = cb.like(cb.lower(root.get("description")), likePattern);
                predicates.add(cb.or(titleMatch, descMatch));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}

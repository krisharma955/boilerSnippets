package com.K955.boilerSnippets.Service;

import com.K955.boilerSnippets.DTOs.Snippet.SnippetRequest;
import com.K955.boilerSnippets.DTOs.Snippet.SnippetResponse;
import com.K955.boilerSnippets.DTOs.Snippet.UpdateSnippetRequest;
import com.K955.boilerSnippets.Enum.Snippet.Framework;
import com.K955.boilerSnippets.Enum.Snippet.Language;
import com.K955.boilerSnippets.Enum.Snippet.SnippetType;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SnippetService {
    SnippetResponse createSnippet(@Valid SnippetRequest request);

    SnippetResponse getSnippetById(Long snippetId);

    Page<SnippetResponse> getAllSnippets(Language language, Framework framework, SnippetType type, String title, Pageable pageable);

    SnippetResponse updateSnippet(Long snippetId, @Valid UpdateSnippetRequest request);

    void deleteSnippet(Long snippetId);
}

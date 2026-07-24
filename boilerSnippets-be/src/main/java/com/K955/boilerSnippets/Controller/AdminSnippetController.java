package com.K955.boilerSnippets.Controller;

import com.K955.boilerSnippets.DTOs.Snippet.SnippetRequest;
import com.K955.boilerSnippets.DTOs.Snippet.SnippetResponse;
import com.K955.boilerSnippets.DTOs.Snippet.UpdateSnippetRequest;
import com.K955.boilerSnippets.Enum.Snippet.Framework;
import com.K955.boilerSnippets.Enum.Snippet.Language;
import com.K955.boilerSnippets.Enum.Snippet.SnippetType;
import com.K955.boilerSnippets.Service.SnippetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/snippets")
public class AdminSnippetController {

    private final SnippetService snippetService;

    @PostMapping
    public ResponseEntity<SnippetResponse> createSnippet(@Valid @RequestBody SnippetRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(snippetService.createSnippet(request));
    }

    @GetMapping("/{snippetId}")
    public ResponseEntity<SnippetResponse> getSnippetById(@PathVariable Long snippetId) {
        return ResponseEntity.ok(snippetService.getSnippetById(snippetId));
    }

    @GetMapping
    public ResponseEntity<Page<SnippetResponse>> getAllSnippets(
            @RequestParam(required = false) Language language,
            @RequestParam(required = false) Framework framework,
            @RequestParam(required = false) SnippetType snippetType,
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 5, sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(snippetService.getAllSnippets(language, framework, snippetType, keyword, pageable));
    }

    @PatchMapping("/{snippetId}")
    public ResponseEntity<SnippetResponse> updateSnippet(@PathVariable Long snippetId,
                                                         @Valid @RequestBody UpdateSnippetRequest request) {
        return ResponseEntity.ok(snippetService.updateSnippet(snippetId, request));
    }

    @DeleteMapping("/{snippetId}")
    public ResponseEntity<Void> deleteSnippet(@PathVariable Long snippetId) {
        snippetService.deleteSnippet(snippetId);
        return ResponseEntity.noContent().build();
    }

}

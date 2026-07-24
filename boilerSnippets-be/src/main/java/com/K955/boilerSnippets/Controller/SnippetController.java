package com.K955.boilerSnippets.Controller;

import com.K955.boilerSnippets.DTOs.Snippet.SnippetResponse;
import com.K955.boilerSnippets.Enum.Snippet.Framework;
import com.K955.boilerSnippets.Enum.Snippet.Language;
import com.K955.boilerSnippets.Enum.Snippet.SnippetType;
import com.K955.boilerSnippets.Service.SnippetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/snippets")
public class SnippetController {

    private final SnippetService snippetService;

    @GetMapping
    public ResponseEntity<Page<SnippetResponse>> searchSnippets(
            @RequestParam(required = false) Language language,
            @RequestParam(required = false) Framework framework,
            @RequestParam(required = false) SnippetType type,
            @RequestParam(required = false) String title,
            @PageableDefault(size = 5, sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(snippetService.getAllSnippets(language, framework, type, title, pageable));
    }

    @GetMapping("/{snippetId}")
    public ResponseEntity<SnippetResponse> getSnippetById(@PathVariable Long snippetId) {
        return ResponseEntity.ok(snippetService.getSnippetById(snippetId));
    }

}

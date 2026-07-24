package com.K955.boilerSnippets.DTOs.Snippet;

import com.K955.boilerSnippets.Enum.Snippet.Framework;
import com.K955.boilerSnippets.Enum.Snippet.Language;
import com.K955.boilerSnippets.Enum.Snippet.SnippetType;

public record UpdateSnippetRequest(
        String title,
        String description,
        Language language,
        Framework framework,
        SnippetType snippetType,
        String frameworkVersion,
        String code,
        String filePath,
        String dependencies
) {
}

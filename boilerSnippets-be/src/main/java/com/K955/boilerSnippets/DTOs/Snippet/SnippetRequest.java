package com.K955.boilerSnippets.DTOs.Snippet;

import com.K955.boilerSnippets.DTOs.User.UserProfileResponse;
import com.K955.boilerSnippets.Enum.Snippet.Framework;
import com.K955.boilerSnippets.Enum.Snippet.Language;
import com.K955.boilerSnippets.Enum.Snippet.SnippetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SnippetRequest(
        @NotBlank String title,
        @NotBlank String description,
        @NotNull Language language,
        Framework framework,
        @NotNull SnippetType snippetType,
        String frameworkVersion,
        @NotBlank String code,
        String filePath,
        String dependencies
) {
}

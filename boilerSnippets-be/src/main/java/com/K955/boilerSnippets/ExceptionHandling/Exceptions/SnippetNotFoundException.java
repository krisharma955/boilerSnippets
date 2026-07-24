package com.K955.boilerSnippets.ExceptionHandling.Exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SnippetNotFoundException extends RuntimeException {
    private final String snippetId;
}

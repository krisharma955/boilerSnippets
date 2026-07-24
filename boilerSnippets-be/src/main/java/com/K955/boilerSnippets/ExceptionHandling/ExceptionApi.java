package com.K955.boilerSnippets.ExceptionHandling;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public record ExceptionApi(
        HttpStatus status,
        String message,
        Instant timestamp
) {
}

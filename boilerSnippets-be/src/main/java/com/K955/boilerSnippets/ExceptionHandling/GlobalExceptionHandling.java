package com.K955.boilerSnippets.ExceptionHandling;

import com.K955.boilerSnippets.ExceptionHandling.Exceptions.BadRequestException;
import com.K955.boilerSnippets.ExceptionHandling.Exceptions.SnippetNotFoundException;
import com.K955.boilerSnippets.ExceptionHandling.Exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionApi> handleUserNotFoundException(UserNotFoundException exception) {
        String message = "User with userId: " +exception.getId()+ " not found.";
        ExceptionApi exceptionApi = new ExceptionApi(
                HttpStatus.NOT_FOUND, message, Instant.now()
        );
        log.error(exceptionApi.message());
        return ResponseEntity.status(exceptionApi.status()).body(exceptionApi);
    }

    @ExceptionHandler(SnippetNotFoundException.class)
    public ResponseEntity<ExceptionApi> handleSnippetNotFoundException(SnippetNotFoundException exception) {
        String message = "Snippet with Id: " +exception.getSnippetId()+ " not found.";
        ExceptionApi exceptionApi = new ExceptionApi(
                HttpStatus.NOT_FOUND, message, Instant.now()
        );
        log.error(exceptionApi.message());
        return ResponseEntity.status(exceptionApi.status()).body(exceptionApi);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionApi> handleSnippetNotFoundException(BadRequestException exception) {
        ExceptionApi exceptionApi = new ExceptionApi(
                HttpStatus.BAD_REQUEST, exception.getMessage(), Instant.now()
        );
        log.error(exceptionApi.message());
        return ResponseEntity.status(exceptionApi.status()).body(exceptionApi);
    }

}

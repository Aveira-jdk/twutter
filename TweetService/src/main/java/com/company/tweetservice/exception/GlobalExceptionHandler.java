package com.company.tweetservice.exception;

import com.company.tweetservice.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ErrorResponse handleNotFoundException(NotFoundException exception) {
        return response(exception, HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler
    public ErrorResponse handleAlreadyExistsException(AlreadyExistsException exception) {
        return response(exception, HttpStatus.ALREADY_REPORTED.value());
    }

    private <T extends RuntimeException> ErrorResponse response(T exception, int statusCode) {
        return new ErrorResponse(statusCode, exception.getMessage(), LocalDateTime.now());
    }
}


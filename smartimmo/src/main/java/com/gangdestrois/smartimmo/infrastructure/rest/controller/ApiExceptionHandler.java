package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.ApiErrorResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException;
import com.gangdestrois.smartimmo.infrastructure.rest.error.AlreadyAssignedAddressException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFoundException(NotFoundException e) {
        return getResponseEntity(
            String.format("%s-not-found", e.getResource()),
            String.format("No %s found with ID %d.", e.getResource(), e.getId()),
            String.format("Ensure that the %s with ID %d exists.", e.getResource(), e.getId()),
            HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return getResponseEntity(
            "not-valid-arguments",
            e.getBody().getTitle(),
            e.getBody().getDetail(),
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ApiErrorResponse> handleJsonMappingException() {
        return getResponseEntity(
            "not-valid-arguments",
            "Bad Request.",
            "Invalid request content.",
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(AlreadyAssignedAddressException.class)
    public ResponseEntity<ApiErrorResponse> handlePropertyAlreadyExistException() {
        return getResponseEntity(
            "already-assigned_address",
            "A property with this address already exists.",
            "Ensure that the property has a non-assigned address.",
            HttpStatus.BAD_REQUEST
        );
    }

    public ResponseEntity<ApiErrorResponse> getResponseEntity(
        String error, String message, String detail,
        HttpStatus httpStatus
    ) {
        ApiErrorResponse response = new ApiErrorResponse(error, message, detail);
        return new ResponseEntity<>(response, httpStatus);
    }
}

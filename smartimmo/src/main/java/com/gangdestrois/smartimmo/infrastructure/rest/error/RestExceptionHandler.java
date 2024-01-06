package com.gangdestrois.smartimmo.infrastructure.rest.error;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.ApiErrorResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException.AlreadyAssignedAddressException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger logger = LogManager.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundException> handleNotFoundException(final Exception notFoundException) {
        return handleException(notFoundException, NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<UnauthorizedException> handleUnauthorizedException(
            final Exception unauthorizedException) {
        return handleException(unauthorizedException, UNAUTHORIZED);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<InternalServerErrorException> handleInternalServerErrorException(
            final Exception internalServerErrorException) {
        return handleException(internalServerErrorException, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException.NotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFoundException(com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException.NotFoundException e) {
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

    private ResponseEntity handleException(Exception exception, HttpStatus httpStatus) {
        exception.printStackTrace();
        return new ResponseEntity<>(
                new ApiErrorResponse(exception.getError(), exception.getMessage(), exception.getDetails()),
                jsonResponseHttpHeaders(),
                httpStatus);
    }

    private static HttpHeaders jsonResponseHttpHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}

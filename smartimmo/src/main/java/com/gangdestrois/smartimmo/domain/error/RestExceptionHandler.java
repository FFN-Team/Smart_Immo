package com.gangdestrois.smartimmo.domain.error;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.ApiErrorResponse;
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

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestException> handleBadRequestException(
            final Exception badRequest) {
        return handleException(badRequest, BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return getResponseEntity(
                ExceptionEnum.NOT_VALID_ARGUMENT,
                e.getBody().getTitle(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ApiErrorResponse> handleJsonMappingException() {
        return getResponseEntity(
                ExceptionEnum.NOT_VALID_ARGUMENT,
                "Bad Request.",
                HttpStatus.BAD_REQUEST
        );
    }

    public ResponseEntity<ApiErrorResponse> getResponseEntity(
            ExceptionEnum error, String message,
            HttpStatus httpStatus) {
        ApiErrorResponse response = new ApiErrorResponse(error, message);
        return new ResponseEntity<>(response, httpStatus);
    }

    private ResponseEntity handleException(Exception exception, HttpStatus httpStatus) {
        exception.printStackTrace();
        return new ResponseEntity<>(
                new ApiErrorResponse(exception.getError(), exception.getMessage()),
                jsonResponseHttpHeaders(),
                httpStatus);
    }

    private static HttpHeaders jsonResponseHttpHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}

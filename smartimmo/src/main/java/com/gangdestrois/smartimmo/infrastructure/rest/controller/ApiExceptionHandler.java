package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.ApiErrorResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException.AlreadyAssignedAddressException;
import com.gangdestrois.smartimmo.infrastructure.rest.error.explicitException.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

}

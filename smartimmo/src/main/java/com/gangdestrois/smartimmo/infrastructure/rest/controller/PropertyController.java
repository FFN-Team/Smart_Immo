package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.property.port.PropertyApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.PropertyResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/biens")
public class PropertyController {
    private final PropertyApi propertyApi;

    public PropertyController(PropertyApi propertyApi) {
        this.propertyApi = propertyApi;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PropertyResponse> retrieve() {
        return propertyApi.findAll().stream().map(PropertyResponse::fromModel).toList();
    }
}
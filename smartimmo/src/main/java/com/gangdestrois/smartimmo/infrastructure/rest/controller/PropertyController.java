package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.property.entite.Property;
import com.gangdestrois.smartimmo.domain.property.port.PropertyApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.PropertyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {
    private final PropertyApi propertyApi;

    public PropertyController(PropertyApi propertyApi) {
        this.propertyApi = propertyApi;
    }

    @GetMapping
    @Operation(summary = "Retrieve all real properties saved.", responses = {
            @ApiResponse(responseCode = "200", description = "All real properties retrieve with success.")
    })
    public ResponseEntity<List<PropertyResponse>> retrieve() {
        return ResponseEntity.ok(propertyApi.findAll().stream().map(PropertyResponse::fromModel).toList());
    }

    @GetMapping("/{propertyId}")
    @ResponseStatus(HttpStatus.OK)
    /*voir si c'est intéressant d'encapsuler les retours dans des ResponseEntity*/
    public PropertyResponse collectPropertyById(@PathVariable Long propertyId) {
        Property property = propertyApi.findPropertyById(propertyId);
        if(nonNull(property)) return PropertyResponse.fromModel(property);
        else {
            /*le throw dans le domain serait peut-être mieux ?*/
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "property not found");
        }
    }
}
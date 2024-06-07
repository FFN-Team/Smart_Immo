package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.property.model.Address;
import com.gangdestrois.smartimmo.domain.property.model.Property;
import com.gangdestrois.smartimmo.domain.property.port.AddressApi;
import com.gangdestrois.smartimmo.domain.property.port.PropertyApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.Request.PropertyRequest;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.Response.PropertyResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.error.BadRequestException;
import com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum;
import com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {
    private final PropertyApi propertyApi;
    private final AddressApi addressApi;

    public PropertyController(PropertyApi propertyApi, AddressApi addressApi) {
        this.propertyApi = propertyApi;
        this.addressApi = addressApi;
    }

    @GetMapping
    @Operation(
            summary = "Get all real properties saved.",
            description = "Returns all real properties saved.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Retrieve successfully."
                    )
            }
    )
    public ResponseEntity<List<PropertyResponse>> retrieve() {
        return ResponseEntity.ok(propertyApi.findAll().stream().map(PropertyResponse::fromModel).toList());
    }


    @GetMapping("/{propertyId}")
    @Operation(
            summary = "Get a property by id.",
            description = "Returns a property as per the id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Retrieve successfully."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Resource not found."
                    )
            }
    )
    @ResponseStatus(HttpStatus.OK)
    /*voir si c'est intéressant d'encapsuler les retours dans des ResponseEntity*/
    public PropertyResponse collectPropertyById(@PathVariable Long propertyId) {
        Property property = propertyApi.findById(propertyId)
                /*le throw dans le domain serait peut-être mieux ?*/
                .orElseThrow(() -> new com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException(ExceptionEnum.PROPERTY_NOT_FOUND,
                        String.format("Property not found for this id : %d", propertyId)));
        return PropertyResponse.fromModel(property);
    }


    @PostMapping
    @Operation(
            summary = "Save a property.",
            description = "Returns the saved property.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Save successfully."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Resource not found."
                    )
            }
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PropertyResponse> save(@Valid @RequestBody PropertyRequest propertyRequest) {
        Address address = getAddress(propertyRequest);
        boolean addressNotAssigned = !propertyApi.existsByAddress(address);

        if (addressNotAssigned) {
            return updateProperties(propertyRequest, null, address);
        } else {
            throw new BadRequestException(ExceptionEnum.ALREADY_ASSIGNED_ADDRESS,
                    String.format("%s already assigned.", address.toString()));
        }
    }


    @PutMapping("/{propertyId}")
    @Operation(
            summary = "Update a property by id.",
            description = "Returns the updated property.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Update successfully."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Resource not found."
                    )
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PropertyResponse> update(@PathVariable Long propertyId, @Valid @RequestBody PropertyRequest propertyRequest) {
        Address address = getAddress(propertyRequest);
        boolean propertyExists = propertyApi.existsById(propertyId);
        boolean addressNotAssigned = !propertyApi.existsPropertyWithAddressIsAndIdIsNot(address, propertyId);

        if (propertyExists && addressNotAssigned) {
            return updateProperties(propertyRequest, propertyId, address);
        } else {
            throw (!propertyExists) ?
                    new com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException(ExceptionEnum.PROPERTY_NOT_FOUND,
                            String.format("Property not found for this id : %d.", propertyId)) :
                    new BadRequestException(ExceptionEnum.ALREADY_ASSIGNED_ADDRESS,
                            String.format("%s already assigned.", address.toString()));
        }
    }


    @DeleteMapping("/{propertyId}")
    @Operation(
            summary = "Delete a property by id.",
            description = "Returns the deleted property.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Delete successfully."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Resource not found."
                    )
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PropertyResponse> delete(@PathVariable Long propertyId) {
        Property property = propertyApi.findById(propertyId)
                .orElseThrow(() -> new com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException(ExceptionEnum.PROPERTY_NOT_FOUND,
                        String.format("Property ot found for this id : %d", propertyId)));
        PropertyResponse propertyResponse = PropertyResponse.fromModel(property);

        propertyApi.deleteById(propertyId);

        return ResponseEntity.ok(propertyResponse);
    }


    public Address getAddress(PropertyRequest propertyRequest) {
        Long idAddress = propertyRequest.getIdAddress();
        return addressApi.findById(idAddress)
                .orElseThrow(() -> new NotFoundException(ExceptionEnum.ADDRESS_NOT_FOUND,
                        String.format("Address not found for this id : %d.", idAddress)));
    }


    public ResponseEntity<PropertyResponse> updateProperties(PropertyRequest propertyRequest,
                                                             Long propertyId, Address address) {
        Property receivedProperty = propertyRequest.toModel(propertyId, address);
        Property updatedProperty = propertyApi.save(receivedProperty);
        PropertyResponse propertyResponse = PropertyResponse.fromModel(updatedProperty);

        return ResponseEntity.ok(propertyResponse);
    }
}

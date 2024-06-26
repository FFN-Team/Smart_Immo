package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.property.model.Address;
import com.gangdestrois.smartimmo.domain.property.port.AddressApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.Response.AddressResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum;
import com.gangdestrois.smartimmo.infrastructure.rest.error.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {
    private final AddressApi addressApi;

    public AddressController(AddressApi addressApi) {
        this.addressApi = addressApi;
    }

    @GetMapping("/non-assigned")
    @Operation(
            summary = "Get all non-assigned addresses.",
            description = "Returns all non-assigned addresses.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Retrieve successfully."
                    )
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AddressResponse>> findNonAssignedAddresses() {
        List<Address> addresses = addressApi.findNonAssignedAddresses();
        return ResponseEntity.ok(addresses.stream().map(AddressResponse::fromModel).toList());
    }

    @GetMapping("/non-assigned/{id}")
    @Operation(
            summary = "Get all non-assigned addresses and the one with ID {id}.",
            description = "Returns all non-assigned addresses and the one with ID {id}.",
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
    public ResponseEntity<List<AddressResponse>> findNonAssignedAddressesPlusAddressWithId(@PathVariable Long id) {
        boolean addressExists = addressApi.existsById(id);

        if (addressExists) {
            List<Address> addresses = addressApi.findNonAssignedAddressesPlusAddressWithId(id);
            return ResponseEntity.ok(addresses.stream().map(AddressResponse::fromModel).toList());
        } else {
            throw new NotFoundException(ExceptionEnum.ADDRESS_NOT_FOUND,
                    String.format("Address with id %d not found", id));
        }
    }
}

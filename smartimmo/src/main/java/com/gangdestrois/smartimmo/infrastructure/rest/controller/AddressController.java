package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.property.entite.Address;
import com.gangdestrois.smartimmo.domain.property.port.AddressApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.AddressResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<AddressResponse>> findByPropertyIsNull() {
        List<Address> addresses = addressApi.findByPropertyIsNull();
        return ResponseEntity.ok(addresses.stream().map(AddressResponse::fromModel).toList());
    }
}

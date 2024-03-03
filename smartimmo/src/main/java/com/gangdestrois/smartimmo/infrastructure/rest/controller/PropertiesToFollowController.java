package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.port.PropertyToFollowApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.PropertyToFollowResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.PropertyToFollowStatusRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties-to-follow")
public class PropertiesToFollowController {
    private final PropertyToFollowApi propertyToFollowApi;
    public PropertiesToFollowController(PropertyToFollowApi propertyToFollowApi) {
        this.propertyToFollowApi = propertyToFollowApi;
    }


    @GetMapping
    @Operation(
            summary = "Get all properties to follow",
            description = "Retrieves all properties to follow.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully retrieved the list of properties to follow.")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public List<PropertyToFollowResponse> collectAll() {
        return propertyToFollowApi.findAll().stream()
                .map(PropertyToFollowResponse::fromModel).toList();
    }



    @PatchMapping("/{propertyToFollowId}/status")
    @Operation(
            summary = "Update status by PropertyToFollow ID",
            description = "Updates the status of a property to follow based on the provided PropertyToFollow ID.",
            parameters = {
                    @Parameter(name = "propertyToFollowId",
                            description = "ID of the PropertyToFollow to update the status for.",in = ParameterIn.PATH,
                            required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Request body containing the updated status for the PropertyToFollow.",
                    required = true, content = @Content(mediaType = "application/json",schema = @Schema(implementation = PropertyToFollowStatusRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200",description = "Status updated successfully."),
                    @ApiResponse(responseCode = "400",description = "Invalid request body or bad request."),
                    @ApiResponse(responseCode = "404",description = "PropertyToFollow not found.")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateStatusByPropertyToFollowId(
            @PathVariable Long propertyToFollowId,
            @RequestBody @Valid @NotBlank PropertyToFollowStatusRequest propertyToFollowStatusRequest) {

        propertyToFollowApi.updateStatusByPropertyToFollowId(propertyToFollowId,
                propertyToFollowStatusRequest.propertyToFollowStatus());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

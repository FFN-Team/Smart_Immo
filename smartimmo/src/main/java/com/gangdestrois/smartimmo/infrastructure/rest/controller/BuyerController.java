package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.buyer.port.BuyerApi;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.port.PropertyToFollowApi;
import com.gangdestrois.smartimmo.domain.property.model.Property;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.BuyerResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.PropertyResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.PropertyToFollowResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/api/v1/buyers")
public class BuyerController {
    private final PropertyToFollowApi propertyToFollowApi;
    private final BuyerApi buyerApi;

    public BuyerController(PropertyToFollowApi propertyToFollowApi, BuyerApi buyerApi) {
        this.propertyToFollowApi = propertyToFollowApi;
        this.buyerApi = buyerApi;
    }


    @GetMapping
    @Operation(
            summary = "Get all buyers",
            description = "Retrieves a list of all buyers.",
            responses = {
                    @ApiResponse(responseCode = "200",description = "Successfully retrieved the list of buyers.")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public List<BuyerResponse> collectAllBuyers() {
        return buyerApi.findAllBuyers().stream()
                .map(BuyerResponse::fromModel).toList();
    }



    @GetMapping("/{buyerId}")
    @Operation(
            summary = "Get buyer by ID",
            description = "Retrieves the details of a buyer based on the provided ID.",
            parameters = {
                    @Parameter(name = "buyerId",description = "ID of the buyer to retrieve.",in = ParameterIn.PATH,
                            required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200",description = "Successfully retrieved the buyer details."),
                    @ApiResponse(responseCode = "404",description = "Buyer not found.")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public BuyerResponse CollectBuyerById(@PathVariable Long buyerId) {
        Buyer buyer = buyerApi.findBuyerById(buyerId);

        if (buyer != null) {
            return BuyerResponse.fromModel(buyer);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Buyer not found");
        }
    }



    @GetMapping("/{buyerId}/properties-to-follow")
    @Operation(
            summary = "Get properties to follow for a buyer",
            description = "Retrieves a list of properties that correspond for a buyer on the provided buyer ID.",
            parameters = {
                    @Parameter(name = "buyerId",description = "ID of the buyer to retrieve properties for.",
                            in = ParameterIn.PATH,required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully retrieved the list of properties to follow."),
                    @ApiResponse(responseCode = "404",
                            description = "Buyer not found found.")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public List<PropertyToFollowResponse> findPropertiesToFollowForBuyer(@PathVariable Long buyerId){
        return propertyToFollowApi.findAllByBuyerId(buyerId).stream()
                .map(PropertyToFollowResponse::fromModel).toList();
    }



    @PutMapping("/{buyerId}/properties-to-follow")
    @Operation(
            summary = "Save properties to follow for a buyer",
            description = "Saves the properties to follow for a buyer based on the provided buyer ID.",
            parameters = {
                    @Parameter(
                            name = "buyerId", description = "ID of the buyer to reset properties for.",
                            in = ParameterIn.PATH, required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200",description = "Properties reset and saved successfully."),
                    @ApiResponse(responseCode = "404",description = "Buyer not found.")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> savePropertiesToFollowForBuyer(@PathVariable Long buyerId)
    {
        propertyToFollowApi.savePropertiesToFollowForBuyer(buyerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
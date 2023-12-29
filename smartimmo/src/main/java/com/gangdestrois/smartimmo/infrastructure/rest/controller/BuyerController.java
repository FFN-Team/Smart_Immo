package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.buyer.port.BuyerApi;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.port.PropertyToFollowApi;
import com.gangdestrois.smartimmo.domain.property.model.Property;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.BuyerResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.PropertyResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.PropertyToFollowResponse;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public List<BuyerResponse> collectAllBuyers() {
        return buyerApi.findAllBuyers().stream()
                .map(BuyerResponse::fromModel).toList();
    }

    @GetMapping("/{buyerId}")
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
    @ResponseStatus(HttpStatus.OK)
    public List<PropertyToFollowResponse> findPropertiesToFollowForBuyer(@PathVariable Long buyerId){
        return propertyToFollowApi.findAllByBuyerId(buyerId).stream()
                .map(PropertyToFollowResponse::fromModel).toList();
    }

    @GetMapping("/{buyerId}/refresh-properties-to-follow")
    @ResponseStatus(HttpStatus.OK)
    public List<PropertyResponse> refreshPropertiesToFollowForBuyer(@PathVariable Long buyerId)
    {
        List<Property> filteredProperties = propertyToFollowApi.savePropertiesToFollowForBuyer(buyerId);
        if (nonNull(filteredProperties)) {
            return filteredProperties
                    .stream()
                    .map(PropertyResponse::fromModel)
                    .toList();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Properties not found");
        }
    }

}
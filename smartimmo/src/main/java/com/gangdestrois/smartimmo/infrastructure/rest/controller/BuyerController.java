package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.buyer.port.BuyerApi;
import com.gangdestrois.smartimmo.domain.buyer.port.PropertiesFinderApi;
import com.gangdestrois.smartimmo.domain.portfolio.model.PropertyPortfolio;
import com.gangdestrois.smartimmo.domain.portfolio.port.PropertiesToFollowApi;
import com.gangdestrois.smartimmo.domain.property.entite.Property;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.BuyerResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.PortfolioPTFResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.PropertyResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/api/v1/buyers")
public class BuyerController {
    private final PropertiesFinderApi propertiesFinderApi;
    private final BuyerApi buyerApi;
    private final PropertiesToFollowApi propertiesToFollowApi;

    public BuyerController(PropertiesFinderApi propertiesFinderApi, BuyerApi buyerApi,
                           PropertiesToFollowApi propertiesToFollowApi) {
        this.propertiesFinderApi = propertiesFinderApi;
        this.buyerApi = buyerApi;
        this.propertiesToFollowApi = propertiesToFollowApi;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BuyerResponse> collectAllBuyers() {
        return buyerApi.findAllBuyers().stream()
                .map(BuyerResponse::fromModel).toList();
    }

    @GetMapping("/{buyerId}")
    @ResponseStatus(HttpStatus.OK)
    public BuyerResponse CollectBuyerById(@PathVariable int buyerId) {
        Buyer buyer = buyerApi.findBuyerById(buyerId);

        if (buyer != null) {
            return BuyerResponse.fromModel(buyer);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Buyer not found");
        }
    }

    @GetMapping("/{buyerId}/filtered-properties")
    @ResponseStatus(HttpStatus.OK)
    public List<PropertyResponse> findPropertiesForBuyer(@PathVariable int buyerId)
    {
        List<Property> filteredProperties = propertiesFinderApi.findPropertiesForBuyer(buyerId);
        if (nonNull(filteredProperties)) {
            return filteredProperties
                    .stream()
                    .map(PropertyResponse::fromModel)
                    .toList();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Properties not found");
        }
    }

    @GetMapping("/{buyerId}/portfolio-PTF")
    @ResponseStatus(HttpStatus.OK)
    public PortfolioPTFResponse createPortfolioPTFForBuyer(@PathVariable int buyerId) {
        PropertyPortfolio propertyPortfolio = propertiesToFollowApi
                .createPortfolio(buyerId);
        return PortfolioPTFResponse.fromModel(propertyPortfolio);
    }
}
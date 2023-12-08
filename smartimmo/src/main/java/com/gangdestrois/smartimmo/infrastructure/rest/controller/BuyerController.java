package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.buyer.port.BuyerApi;
import com.gangdestrois.smartimmo.domain.buyer.port.PropertiesFinderApi;
import com.gangdestrois.smartimmo.domain.property.entite.Property;
import com.gangdestrois.smartimmo.domain.portfolio.model.PortfolioPropertiesToFollow;
import com.gangdestrois.smartimmo.domain.portfolio.port.PortfolioPropertiesToFollowApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.BuyerResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.PropertyResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.PortfolioPTFResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/buyers")
public class BuyerController {
    private final PropertiesFinderApi propertiesFinderApi;
    private final BuyerApi buyerApi;
    private final PortfolioPropertiesToFollowApi portfolioPropertiesToFollowApi;

    public BuyerController(PropertiesFinderApi propertiesFinderApi, BuyerApi buyerApi,
                           PortfolioPropertiesToFollowApi portfolioPropertiesToFollowApi) {
        this.propertiesFinderApi = propertiesFinderApi;
        this.buyerApi = buyerApi;
        this.portfolioPropertiesToFollowApi = portfolioPropertiesToFollowApi;
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

    @GetMapping("/{buyerId}/filtred-properties")
    @ResponseStatus(HttpStatus.OK)
    public List<PropertyResponse> findPropertiesForBuyer(@PathVariable int buyerId)
    {
        List<Property> biensFiltred = propertiesFinderApi.findPropertiesForBuyer(buyerId);

        if (biensFiltred != null) {
            return biensFiltred
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
        PortfolioPropertiesToFollow portfolioPropertiesToFollow = portfolioPropertiesToFollowApi
                .createPortfolioPropertiesToFollowApi(buyerId);
        return PortfolioPTFResponse.fromModel(portfolioPropertiesToFollow);
    }
}
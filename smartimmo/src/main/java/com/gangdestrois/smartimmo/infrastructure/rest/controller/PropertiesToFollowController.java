package com.gangdestrois.smartimmo.infrastructure.rest.controller;

import com.gangdestrois.smartimmo.domain.buyer.port.BuyerApi;
import com.gangdestrois.smartimmo.domain.portfolio.propertiesToFollow.port.PropertyToFollowApi;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.BuyerResponse;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.PropertyToFollowResponse;
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
    @ResponseStatus(HttpStatus.OK)
    public List<PropertyToFollowResponse> collectAll() {
        return propertyToFollowApi.findAll().stream()
                .map(PropertyToFollowResponse::fromModel).toList();
    }

    @PatchMapping("/{PTFId}/status")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateStatusByPropertyToFollowId(
            @PathVariable Long propertyToFollowId,
            @RequestBody @Valid @NotBlank String newStatus) {

        propertyToFollowApi.updateStatusByPropertyToFollowId(propertyToFollowId, newStatus);

        return new ResponseEntity<>("Mise à jour réussie", HttpStatus.OK);
    }
}

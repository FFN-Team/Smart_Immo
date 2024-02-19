package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.buyer.enums.BuyerStatus;
import com.gangdestrois.smartimmo.domain.property.PropertyCriteria;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.time.LocalDate;
import java.util.Date;

public record BuyerResponse(Long id, Prospect prospect, BuyerStatus status, LocalDate searchStartDate,
                            LocalDate searchEndDate, PropertyCriteria propertyCriteria) {
    public static BuyerResponse fromModel(Buyer buyer) {
        return new BuyerResponse(buyer.getId(), buyer.getProspect(), buyer.getStatus(),
                buyer.getSearchStartDate(), buyer.getSearchEndDate(),
                buyer.getPropertyCriteria());
    }
}

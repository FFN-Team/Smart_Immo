package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.buyer.enums.BuyerStatusEnum;
import com.gangdestrois.smartimmo.domain.property.PropertyCriteria;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.Date;

public record BuyerResponse(Long id, Prospect prospect, BuyerStatusEnum status, Date searchStartDate, Date searchEndDate,
                            PropertyCriteria propertyCriteria) {
    public static BuyerResponse fromModel(Buyer buyer) {
        return new BuyerResponse(buyer.getId(), buyer.getProspect(), buyer.getStatus(),
                buyer.getSearchStartDate(), buyer.getSearchEndDate(),
                buyer.getPropertyCriteria());
    }
}

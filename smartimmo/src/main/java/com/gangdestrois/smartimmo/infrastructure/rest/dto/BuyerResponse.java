package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.buyer.model.Buyer;
import com.gangdestrois.smartimmo.domain.buyer.model.PropertyCriteria;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

public record BuyerResponse(Long id, Prospect prospect, String statut, String dateDebutRecherche, String dateFinRecherche,
                            PropertyCriteria propertyCriteria) {
    public static BuyerResponse fromModel(Buyer buyer) {
        return new BuyerResponse(buyer.getId(), buyer.getProspect(), buyer.getStatus(),
                buyer.getSearchStartDate(), buyer.getSearchEndDate(),
                buyer.getPropertyCriteria());
    }
}

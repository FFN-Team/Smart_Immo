package com.gangdestrois.smartimmo.domain.buyer.model;

import com.gangdestrois.smartimmo.domain.buyer.enums.BuyerStatus;
import com.gangdestrois.smartimmo.domain.property.PropertyCriteria;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.time.LocalDate;
import java.util.Date;

public class Buyer {
    private final Long id;
    private final Prospect prospect;
    private final BuyerStatus status;
    private final LocalDate searchStartDate;
    private final LocalDate searchEndDate;
    private final PropertyCriteria propertyCriteria;

    public Buyer(Long id, Prospect prospect, BuyerStatus status, LocalDate searchStartDate, LocalDate searchEndDate) {
        this.id = id;
        this.prospect=prospect;
        this.status = status;
        this.searchStartDate = searchStartDate;
        this.searchEndDate = searchEndDate;
        this.propertyCriteria =null;
    }

    public Buyer(Long id, Prospect prospect, BuyerStatus status, LocalDate searchStartDate, LocalDate searchEndDate,
                 PropertyCriteria propertyCriteria) {
        this.id = id;
        this.prospect=prospect;
        this.status = status;
        this.searchStartDate = searchStartDate;
        this.searchEndDate = searchEndDate;
        this.propertyCriteria = propertyCriteria;
    }


    public Long getId() { return id; }
    public BuyerStatus getStatus() { return status; }
    public LocalDate getSearchStartDate() { return searchStartDate; }
    public LocalDate getSearchEndDate() { return searchEndDate; }
    public PropertyCriteria getPropertyCriteria() { return propertyCriteria; }
    public Prospect getProspect() { return prospect; }
}

package com.gangdestrois.smartimmo.domain.buyer.model;

import com.gangdestrois.smartimmo.domain.buyer.enums.BuyerStatusEnum;
import com.gangdestrois.smartimmo.domain.property.PropertyCriteria;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.Date;

public class Buyer {
    private final Long id;
    private final Prospect prospect;
    private final BuyerStatusEnum status;
    private final Date searchStartDate;
    private final Date searchEndDate;
    private final PropertyCriteria propertyCriteria;

    public Buyer(Long id, Prospect prospect, BuyerStatusEnum status, Date searchStartDate, Date searchEndDate) {
        this.id = id;
        this.prospect=prospect;
        this.status = status;
        this.searchStartDate = searchStartDate;
        this.searchEndDate = searchEndDate;
        this.propertyCriteria =null;
    }

    public Buyer(Long id, Prospect prospect, BuyerStatusEnum status, Date searchStartDate, Date searchEndDate,
                 PropertyCriteria propertyCriteria) {
        this.id = id;
        this.prospect=prospect;
        this.status = status;
        this.searchStartDate = searchStartDate;
        this.searchEndDate = searchEndDate;
        this.propertyCriteria = propertyCriteria;
    }


    public Long getId() { return id; }
    public BuyerStatusEnum getStatus() { return status; }
    public Date getSearchStartDate() { return searchStartDate; }
    public Date getSearchEndDate() { return searchEndDate; }
    public PropertyCriteria getPropertyCriteria() { return propertyCriteria; }
    public Prospect getProspect() { return prospect; }
}

package com.gangdestrois.smartimmo.domain.buyer.model;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

public class Buyer {
    private Long id;
    private Prospect prospect;
    private String status;
    private String searchStartDate;
    private String searchEndDate;
    private PropertyCriteria propertyCriteria;

    public Buyer(Long id, Prospect prospect, String status, String searchStartDate, String searchEndDate,
                 PropertyCriteria propertyCriteria) {
        this.id = id;
        this.prospect=prospect;
        this.status = status;
        this.searchStartDate = searchStartDate;
        this.searchEndDate = searchEndDate;
        this.propertyCriteria = propertyCriteria;
    }


    public Long getId() { return id; }
    public String getStatus() { return status; }
    public String getSearchStartDate() { return searchStartDate; }
    public String getSearchEndDate() { return searchEndDate; }
    public PropertyCriteria getPropertyCriteria() { return propertyCriteria; }
    public Prospect getProspect() { return prospect; }
}

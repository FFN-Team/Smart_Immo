package com.gangdestrois.smartimmo.domain.buyer.model;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

public class Buyer {
    private Long id;
    private Prospect prospect;
    //enum
    private String status;
    //plutôt une LocalDate non ?
    private String searchStartDate;
    //idem ?
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

    /*on peut faire gaffe à la génération des getters automatique*/

    public Long getId() { return id; }
    public String getStatus() { return status; }
    public String getSearchStartDate() { return searchStartDate; }
    public String getSearchEndDate() { return searchEndDate; }
    public PropertyCriteria getPropertyCriteria() { return propertyCriteria; }
    public Prospect getProspect() { return prospect; }
}

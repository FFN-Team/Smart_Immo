package com.gangdestrois.smartimmo.domain.agenda.model;

import com.gangdestrois.smartimmo.domain.property.model.Property;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.google.api.client.util.DateTime;

import java.util.Date;

public class Visit {
    private String prospect;
    private String adresse;
    private DateTime startDateTime;
    private DateTime endDateTime;
    private String visitType;
    private String comments;

    public Visit(String prospect, String adresse, DateTime dateTime, DateTime endDateTime,String visitType, String comments) {
        this.prospect = prospect;
        this.adresse = adresse;
        this.startDateTime = dateTime;
        this.visitType = visitType;
        this.comments = comments;
        this.endDateTime=endDateTime;
    }

    public String getProspect() {
        return prospect;
    }
    public String getAdresse() {
        return adresse;
    }
    public DateTime getStartDateTime() {
        return startDateTime;
    }
    public String getVisitType() {
        return visitType;
    }
    public String getComments() {
        return comments;
    }
    public DateTime getEndDateTime() {
        return endDateTime;
    }
}

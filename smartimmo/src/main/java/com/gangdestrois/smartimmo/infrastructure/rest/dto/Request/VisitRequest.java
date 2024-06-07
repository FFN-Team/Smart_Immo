package com.gangdestrois.smartimmo.infrastructure.rest.dto.Request;

import com.gangdestrois.smartimmo.domain.agenda.model.Visit;
import com.gangdestrois.smartimmo.domain.property.model.Property;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.google.api.client.util.DateTime;

public record VisitRequest(String prospect, String adresse, DateTime startDateTime, DateTime endDateTime,
                           String visitType, String comments) {
    public Visit toModel(){
        return new Visit(prospect,adresse,startDateTime,endDateTime,visitType,comments);
    }
}

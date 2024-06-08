package com.gangdestrois.smartimmo.domain.agenda.port;

import com.gangdestrois.smartimmo.domain.agenda.model.Visit;

import java.io.IOException;

public interface VisitSaver {
    public void saveVisit(Visit visit) throws IOException;
}

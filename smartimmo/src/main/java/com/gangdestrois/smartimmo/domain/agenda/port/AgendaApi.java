package com.gangdestrois.smartimmo.domain.agenda.port;

import com.gangdestrois.smartimmo.domain.agenda.model.Visit;

import java.io.IOException;


public interface AgendaApi {
     void addVisitToAgenda(Visit visit) throws IOException;
}

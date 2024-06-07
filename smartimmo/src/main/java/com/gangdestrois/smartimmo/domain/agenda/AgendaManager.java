package com.gangdestrois.smartimmo.domain.agenda;

import com.gangdestrois.smartimmo.domain.agenda.model.Visit;
import com.gangdestrois.smartimmo.domain.agenda.port.AgendaApi;
import com.gangdestrois.smartimmo.domain.agenda.port.VisitSaver;

import java.io.IOException;

public class AgendaManager implements AgendaApi {
    VisitSaver visitSaver;

    public AgendaManager(VisitSaver visitSaver){
        this.visitSaver=visitSaver;
    }
    public AgendaManager(){}

    @Override
    public void addVisitToAgenda(Visit visit) {
        try {
            visitSaver.saveVisit(visit);
        }catch (Exception e){
            System.out.println(e);
        }

    }
}

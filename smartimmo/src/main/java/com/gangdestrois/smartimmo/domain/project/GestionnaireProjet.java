package com.gangdestrois.smartimmo.domain.project;

import com.gangdestrois.smartimmo.domain.notification.EventManager;
import com.gangdestrois.smartimmo.domain.project.port.ProjetAnticipeApi;
import com.gangdestrois.smartimmo.domain.project.port.ProjetSpi;

import java.time.LocalDate;

import static com.gangdestrois.smartimmo.domain.notification.EventType.PROJET_DATE_PREVUE_APPROCHE;

public class GestionnaireProjet implements ProjetAnticipeApi {
    private ProjetSpi projetSpi;
    private EventManager eventManager;

    public GestionnaireProjet(ProjetSpi projetSpi, EventManager eventManager) {
        this.projetSpi = projetSpi;
        this.eventManager = eventManager;
    }

    public void notifyProjetsAnticipes() {
        projetSpi.findProjetsByDatePrevue(LocalDate.now().plusMonths(6))
                .forEach(projet -> eventManager.notify(PROJET_DATE_PREVUE_APPROCHE, projet.mapToEvent()));
    }
}

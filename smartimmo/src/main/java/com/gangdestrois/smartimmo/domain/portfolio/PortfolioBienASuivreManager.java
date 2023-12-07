package com.gangdestrois.smartimmo.domain.portfolio;

import com.gangdestrois.smartimmo.domain.acquereur.port.AcquereurSpi;
import com.gangdestrois.smartimmo.domain.acquereur.port.PropertiesFinderApi;
import com.gangdestrois.smartimmo.domain.portfolio.entite.PortfolioBienASuivre;
import com.gangdestrois.smartimmo.domain.portfolio.port.PortfolioBienASuivreManagerApi;

import java.util.Date;

public class PortfolioBienASuivreManager implements PortfolioBienASuivreManagerApi {
    private PropertiesFinderApi propertiesFinderApi;
    private AcquereurSpi acquereurSpi;

    public PortfolioBienASuivreManager(PropertiesFinderApi propertiesFinderApi, AcquereurSpi acquereurSpi) {
        this.propertiesFinderApi = propertiesFinderApi;
        this.acquereurSpi=acquereurSpi;
    }

    @Override
    public PortfolioBienASuivre createPortfolioBienASuivre(int id){
        PortfolioBienASuivre portfolioBienASuivre1=new PortfolioBienASuivre(new Date());
        portfolioBienASuivre1.setAcquereur(acquereurSpi.findAcquereurById(id));
        portfolioBienASuivre1.setBiensASuivre(propertiesFinderApi.findPropertiesForBuyer(id));
        return  portfolioBienASuivre1;
    }
}

package com.gangdestrois.smartimmo.infrastructure.configuration;

import com.gangdestrois.smartimmo.domain.acquereur.PropertiesFinder;
import com.gangdestrois.smartimmo.domain.portfolio.PortfolioBienASuivreManager;
import com.gangdestrois.smartimmo.infrastructure.jpa.AcquereurDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.BienDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.BienRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.CriteresBienAcquereurRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public BienDataAdapter BienDataAdapter(BienRepository bienRepository) {
        return new BienDataAdapter(bienRepository);
    }

    @Bean
    public AcquereurDataAdapter acquereurDataAdapter(CriteresBienAcquereurRepository criteresBienAcquereurRepository)
    {return new AcquereurDataAdapter(criteresBienAcquereurRepository);}

    @Bean
    public PropertiesFinder propertiesFinder(AcquereurDataAdapter acquereurDataAdapter, BienDataAdapter bienDataAdapter){
        return new PropertiesFinder(acquereurDataAdapter,bienDataAdapter);
    }

    @Bean
    public PortfolioBienASuivreManager portfolioBienASuivreManager(PropertiesFinder propertiesFinder,
                                                                   AcquereurDataAdapter acquereurDataAdapter){
        return new PortfolioBienASuivreManager(propertiesFinder,acquereurDataAdapter);
    }
}
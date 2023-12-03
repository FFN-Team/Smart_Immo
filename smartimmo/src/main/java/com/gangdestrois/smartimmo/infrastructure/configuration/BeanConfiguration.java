package com.gangdestrois.smartimmo.infrastructure.configuration;

import com.gangdestrois.smartimmo.domain.acquereur.AcquereurManager;
import com.gangdestrois.smartimmo.domain.bien.BienService;
import com.gangdestrois.smartimmo.infrastructure.jpa.AcquereurDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.BienDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.CriteresBienAcquereurRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.AcquereurRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.BienRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public BienDataAdapter BienDataAdapter(BienRepository bienRepository) {
        return new BienDataAdapter(bienRepository);
    }

    @Bean
    public BienService bienService(BienDataAdapter bienDataAdapter) {
        return new BienService(bienDataAdapter);
    }

    @Bean
    public AcquereurDataAdapter acquereurDataAdapter(CriteresBienAcquereurRepository acquereurCriteresBienRepository)
    {return new AcquereurDataAdapter(acquereurCriteresBienRepository);}

    @Bean
    public AcquereurManager acquereurManager(AcquereurDataAdapter acquereurDataAdapter){return new AcquereurManager(acquereurDataAdapter);}
}
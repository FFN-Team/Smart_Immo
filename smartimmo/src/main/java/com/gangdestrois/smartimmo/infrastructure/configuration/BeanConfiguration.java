package com.gangdestrois.smartimmo.infrastructure.configuration;

import com.gangdestrois.smartimmo.domain.bien.BienService;
import com.gangdestrois.smartimmo.domain.notification.EventManager;
import com.gangdestrois.smartimmo.domain.project.GestionnaireProjet;
import com.gangdestrois.smartimmo.infrastructure.jpa.BienDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.ProjetAnticipeDataAdapter;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.BienRepository;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.ProjetAnticipeRepository;
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
    public ProjetAnticipeDataAdapter ProjetAnticipeDataAdapter(ProjetAnticipeRepository projetAnticipeRepository) {
        return new ProjetAnticipeDataAdapter(projetAnticipeRepository);
    }

    @Bean
    public GestionnaireProjet bienService(ProjetAnticipeDataAdapter projetAnticipeDataAdapter) {
        return new GestionnaireProjet(projetAnticipeDataAdapter, new EventManager());
    }
}
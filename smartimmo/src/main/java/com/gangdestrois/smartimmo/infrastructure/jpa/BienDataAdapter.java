package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.bien.Bien;
import com.gangdestrois.smartimmo.domain.bien.port.BienPort;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.BienEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.BienRepository;

import java.util.List;

public class BienDataAdapter implements BienPort {
    private final BienRepository bienRepository;

    public BienDataAdapter(BienRepository bienRepository) {
        this.bienRepository = bienRepository;
    }

    @Override
    public List<Bien> findAll() {
        return bienRepository.findAll()
                .stream()
                .map(BienEntity::toModel)
                .toList();
    }
}

package com.gangdestrois.smartimmo.domain.bien.service;

import com.gangdestrois.smartimmo.domain.bien.model.Bien;
import com.gangdestrois.smartimmo.domain.bien.port.api.BienApi;
import com.gangdestrois.smartimmo.domain.bien.port.spi.BienPort;
import com.gangdestrois.smartimmo.domain.common.DomainComponent;

import java.util.List;

@DomainComponent
public class BienService implements BienApi {
    private final BienPort bienPort;

    public BienService(BienPort bienPort) {
        this.bienPort = bienPort;
    }

    public List<Bien> retrieve() {
        return bienPort.findAll();
    }
}

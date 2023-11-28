package com.gangdestrois.smartimmo.domain.bien;

import com.gangdestrois.smartimmo.domain.bien.port.BienApi;
import com.gangdestrois.smartimmo.domain.bien.port.BienPort;
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

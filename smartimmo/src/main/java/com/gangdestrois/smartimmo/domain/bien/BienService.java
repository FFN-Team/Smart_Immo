package com.gangdestrois.smartimmo.domain.bien;

import com.gangdestrois.smartimmo.domain.bien.port.BienApi;
import com.gangdestrois.smartimmo.domain.bien.port.BienSpi;
import com.gangdestrois.smartimmo.domain.bien.port.BienPort;
import com.gangdestrois.smartimmo.domain.common.DomainComponent;

import java.util.List;

@DomainComponent
public class BienService implements BienApi {
    private final BienSpi bienSpi;

    public BienService(BienSpi bienSpi) {
        this.bienSpi = bienSpi;
    }

    public List<Bien> retrieve() {
        return bienSpi.findAll();
    }
}

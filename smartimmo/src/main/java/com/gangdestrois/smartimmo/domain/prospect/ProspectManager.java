package com.gangdestrois.smartimmo.domain.prospect;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectManagerApi;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;

import java.util.List;

public class ProspectManager implements ProspectManagerApi {
    private final ProspectSpi prospectSpi;

    public ProspectManager(ProspectSpi prospectSpi) {
        this.prospectSpi = prospectSpi;
    }

    @Override
    public List<Prospect> findAll() {
        return prospectSpi.findAll();
    }
}

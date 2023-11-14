package com.gangdestrois.smartimmo.domain.bien.service;

import com.gangdestrois.smartimmo.domain.bien.model.Bien;
import com.gangdestrois.smartimmo.domain.bien.port.spi.BienPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BienService {
    private BienPort bienPort;

    public List<Bien> getBien() {
        var biens = bienPort.findAll();
        return biens;
    }

}

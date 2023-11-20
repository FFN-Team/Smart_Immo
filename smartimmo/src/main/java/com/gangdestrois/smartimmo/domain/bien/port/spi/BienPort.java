package com.gangdestrois.smartimmo.domain.bien.port.spi;

import com.gangdestrois.smartimmo.domain.bien.model.Bien;

import java.util.List;

public interface BienPort {
    List<Bien> findAll();
}
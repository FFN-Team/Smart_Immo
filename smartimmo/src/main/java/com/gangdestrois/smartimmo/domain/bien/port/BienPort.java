package com.gangdestrois.smartimmo.domain.bien.port;

import com.gangdestrois.smartimmo.domain.bien.Bien;

import java.util.List;

public interface BienPort {
    List<Bien> findAll();
}
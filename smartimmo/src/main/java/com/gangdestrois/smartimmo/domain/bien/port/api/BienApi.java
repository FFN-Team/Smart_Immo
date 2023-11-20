package com.gangdestrois.smartimmo.domain.bien.port.api;

import com.gangdestrois.smartimmo.domain.bien.model.Bien;

import java.util.List;

public interface BienApi {
    public List<Bien> retrieve();
}

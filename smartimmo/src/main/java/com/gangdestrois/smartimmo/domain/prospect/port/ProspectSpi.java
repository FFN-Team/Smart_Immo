package com.gangdestrois.smartimmo.domain.prospect.port;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.List;

public interface ProspectSpi {
    List<Prospect> findAll();
    long countByAgeBetween(int ageMin, int ageMax);
    List<Object[]> countByProfession();
    List<Object[]> countByContactOrigin();
}

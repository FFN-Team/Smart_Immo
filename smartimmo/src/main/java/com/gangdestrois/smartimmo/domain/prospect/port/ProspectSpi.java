package com.gangdestrois.smartimmo.domain.prospect.port;

import com.gangdestrois.smartimmo.domain.prospect.ProspectStatistic;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.List;

public interface ProspectSpi {
//    List<Prospect> findProspectsThatMayExpandTheirFamily(MaritalStatus maritalStatus,Integer yearsSinceBuy,Integer roomNumberOfProperty);

    List<Prospect> findAll();
    long countByAgeBetween(int ageMin, int ageMax);
    List<Object[]> countByProfession();
    List<Object[]> countByContactOrigin();
    List<ProspectStatistic> countByProfessionExample();
    List<ProspectStatistic> countByContactOriginExample();
}

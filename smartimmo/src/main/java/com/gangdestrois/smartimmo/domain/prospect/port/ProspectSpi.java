package com.gangdestrois.smartimmo.domain.prospect.port;

import com.gangdestrois.smartimmo.domain.prospect.ProspectStatistic;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.List;
import java.util.Optional;

public interface ProspectSpi {
//    List<Prospect> findProspectsThatMayExpandTheirFamily(MaritalStatus maritalStatus,Integer yearsSinceBuy,Integer roomNumberOfProperty);

    List<Prospect> findAll();

    long countByAgeBetween(int ageMin, int ageMax);

    List<ProspectStatistic> countByProfession();

    List<ProspectStatistic> countByContactOrigin();

    Optional<Prospect> findById(Long prospectId);
}

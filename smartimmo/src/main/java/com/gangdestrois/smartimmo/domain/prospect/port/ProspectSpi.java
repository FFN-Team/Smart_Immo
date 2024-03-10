package com.gangdestrois.smartimmo.domain.prospect.port;

import com.gangdestrois.smartimmo.domain.filter.prospect.enums.MathematicalComparator;
import com.gangdestrois.smartimmo.domain.prospect.enums.ContactOrigin;
import com.gangdestrois.smartimmo.domain.prospect.enums.Profession;
import com.gangdestrois.smartimmo.domain.prospect.enums.Title;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.domain.prospect.model.ProspectStatistic;
import com.gangdestrois.smartimmo.domain.utils.ModelSpi;

import java.util.List;
import java.util.Optional;

public interface ProspectSpi extends ModelSpi<Prospect> {
    // List<Prospect> findProspectsThatMayExpandTheirFamily(MaritalStatus maritalStatus,Integer yearsSinceBuy,Integer roomNumberOfProperty);
    List<Prospect> findAll();

    long countByAgeBetween(int ageMin, int ageMax);

    List<ProspectStatistic> countByProfession();

    List<ProspectStatistic> countByContactOrigin();

    Optional<Prospect> findById(Long prospectId);
    List<Prospect> findAllByAge(Integer age, MathematicalComparator ageComparator);

    List<Prospect> findAllByContactOrigin(ContactOrigin contactOrigin);

    List<Prospect> findAllByTitle(Title title);

    List<Prospect> findAllByProfession(Profession profession);

    List<Prospect> findAllByAuthorizeContactOnSocialMedia(Boolean authorizeContactOnSocialMedia);
}

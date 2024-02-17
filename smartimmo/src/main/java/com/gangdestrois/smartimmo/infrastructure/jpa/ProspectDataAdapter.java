package com.gangdestrois.smartimmo.infrastructure.jpa;

import com.gangdestrois.smartimmo.domain.filter.prospect.MathematicalComparator;
import com.gangdestrois.smartimmo.domain.prospect.ContactOrigin;
import com.gangdestrois.smartimmo.domain.prospect.Profession;
import com.gangdestrois.smartimmo.domain.prospect.ProspectStatistic;
import com.gangdestrois.smartimmo.domain.prospect.Title;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.domain.prospect.port.ProspectSpi;
import com.gangdestrois.smartimmo.infrastructure.jpa.entity.ProspectEntity;
import com.gangdestrois.smartimmo.infrastructure.jpa.repository.ProspectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProspectDataAdapter implements ProspectSpi {
    private final ProspectRepository prospectRepository;

    @Autowired
    public ProspectDataAdapter(ProspectRepository prospectRepository) {
        this.prospectRepository = prospectRepository;
    }

    @Override
    public List<Prospect> findAll() {
        return prospectRepository.findAll().stream().map(ProspectEntity::toModel).toList();
    }

    @Override
    public long countByAgeBetween(int ageMin, int ageMax) {
        return prospectRepository.countByAgeBetween(ageMin, ageMax);
    }

    @Override
    public List<ProspectStatistic> countByProfession() {
        return prospectRepository.countByProfession().stream()
                .map(prospectStatistic -> new ProspectStatistic(((Profession) prospectStatistic[0]).getLabel(), (long) prospectStatistic[1]))
                .toList();
    }

    @Override
    public List<ProspectStatistic> countByContactOrigin() {
        return prospectRepository.countByContactOrigin().stream()
                .map(prospectStatistic -> new ProspectStatistic(((ContactOrigin) prospectStatistic[0]).getLabel(), (long) prospectStatistic[1]))
                .toList();
    }

    @Override
    public List<Prospect> findAllByAge(Integer age, MathematicalComparator ageComparator) {
        return prospectRepository.findAllByAge(age, ageComparator.name()).stream().map(ProspectEntity::toModel).toList();
    }

    @Override
    public List<Prospect> findAllByContactOrigin(ContactOrigin contactOrigin) {
        return prospectRepository.findAllByContactOrigin(contactOrigin).stream().map(ProspectEntity::toModel).toList();
    }

    @Override
    public List<Prospect> findAllByTitle(Title title) {
        return prospectRepository.findAllByTitle(title).stream().map(ProspectEntity::toModel).toList();
    }

    @Override
    public List<Prospect> findAllByProfession(Profession profession) {
        return prospectRepository.findAllByProfession(profession).stream().map(ProspectEntity::toModel).toList();
    }

    @Override
    public List<Prospect> findAllByAuthorizeContactOnSocialMedia(Boolean authorizeContactOnSocialMedia) {
        return prospectRepository.findAllByAuthorizeContactOnSocialMedia(authorizeContactOnSocialMedia)
                .stream().map(ProspectEntity::toModel).toList();
    }


    @Override
    public Optional<Prospect> findById(Long prospectId) {
        return prospectRepository.findById(prospectId).map(ProspectEntity::toModel);
    }
}

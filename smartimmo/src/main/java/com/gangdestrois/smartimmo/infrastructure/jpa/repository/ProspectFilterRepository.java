package com.gangdestrois.smartimmo.infrastructure.jpa.repository;

import com.gangdestrois.smartimmo.infrastructure.jpa.entity.ProspectFilterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProspectFilterRepository extends JpaRepository<ProspectFilterEntity,Long> {
     boolean existsAllByProspectFilterName(String prospectFilterName);
     ProspectFilterEntity findByProspectFilterName(String prospectFilterName);
}

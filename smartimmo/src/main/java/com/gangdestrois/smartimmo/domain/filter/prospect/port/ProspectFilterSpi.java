package com.gangdestrois.smartimmo.domain.filter.prospect.port;

import com.gangdestrois.smartimmo.domain.filter.prospect.model.ProspectFilter;

import java.util.List;

public interface ProspectFilterSpi {
    void saveProspectFilter(ProspectFilter prospectFilter);

    boolean existsAllByProspectFilterName(String prospectFilterName);

    ProspectFilter findByProspectFilterName(String prospectFilterName);

    Integer deleteByProspectFilterName(String prospectFilterName);

    List<ProspectFilter> findAll();
}

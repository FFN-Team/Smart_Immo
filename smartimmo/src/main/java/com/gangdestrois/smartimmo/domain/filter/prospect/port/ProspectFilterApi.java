package com.gangdestrois.smartimmo.domain.filter.prospect.port;

import com.gangdestrois.smartimmo.domain.filter.prospect.model.ProspectFilter;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.List;

public interface ProspectFilterApi {
    List<Prospect> filterProspects(ProspectFilter prospectFilter);

    void saveProspectFilter(ProspectFilter prospectFilter);

    ProspectFilter findByProspectFilterName(String prospectFilterName);

    Integer deleteByProspectFilterName(String prospectFilterName);

    List<ProspectFilter> findAll();
}

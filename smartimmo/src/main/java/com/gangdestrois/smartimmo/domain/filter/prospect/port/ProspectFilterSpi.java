package com.gangdestrois.smartimmo.domain.filter.prospect.port;

import com.gangdestrois.smartimmo.domain.filter.prospect.model.ProspectFilter;

public interface ProspectFilterSpi {
    void saveProspectFilter(ProspectFilter prospectFilter);
    boolean existsAllByProspectFilterName(String prospectFilterName);
    ProspectFilter findByProspectFilterName(String prospectFilterName);
}

package com.gangdestrois.smartimmo.domain.prospect.port;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import com.gangdestrois.smartimmo.infrastructure.rest.dto.ProspectStatisticsResponse;

import java.util.List;
import java.util.Map;

public interface ProspectApi {
    List<Prospect> findAll();
    ProspectStatisticsResponse countByAgeGroup();
    ProspectStatisticsResponse countByProfession();
    ProspectStatisticsResponse countByContactOrigin();
}

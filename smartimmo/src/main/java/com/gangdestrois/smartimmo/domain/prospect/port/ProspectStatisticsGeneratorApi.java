package com.gangdestrois.smartimmo.domain.prospect.port;

import com.gangdestrois.smartimmo.infrastructure.rest.dto.ProspectStatisticsResponse;

public interface ProspectStatisticsGeneratorApi {
    ProspectStatisticsResponse countByAgeGroup();
    ProspectStatisticsResponse countByProfession();
    ProspectStatisticsResponse countByContactOrigin();
}

package com.gangdestrois.smartimmo.infrastructure.rest.dto.Response;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.List;

@RecordBuilder
public record ProspectStatisticsResponse(String title, List<ProspectDataResponse> data) {
}

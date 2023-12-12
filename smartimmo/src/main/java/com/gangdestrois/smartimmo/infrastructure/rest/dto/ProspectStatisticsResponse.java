package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.List;

@RecordBuilder
public record ProspectStatisticsResponse(String title, List<ProspectDataResponse> data) {
}

package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.property.model.Area;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record AreaResponse(Long id, String name, Double surface_area) {
    public static AreaResponse fromModel(Area area) {
        return new AreaResponse(
                area.id(),
                area.areaName(),
                area.areaSurfaceArea()
        );
    }
}

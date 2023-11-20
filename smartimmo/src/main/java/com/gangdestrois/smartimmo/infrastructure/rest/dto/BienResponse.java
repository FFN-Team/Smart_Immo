package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.bien.model.Bien;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record BienResponse(String nomBien, String description) {
    public static BienResponse fromModel(Bien bien) {
        return new BienResponse(bien.nomBien(), bien.description());
    }
}

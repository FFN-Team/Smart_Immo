package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.bien.Bien;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record BienResponse(Long id, String nomBien, String description, int nbPiece, double surfaceHabitable) {
    public static BienResponse fromModel(Bien bien) {
        return new BienResponse(bien.id(), bien.nomBien(), bien.description(), bien.nbPiece(), bien.surfaceHabitable());
    }
}

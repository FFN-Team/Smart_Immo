package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

public record ProspectResponse(Integer id) {
    public static ProspectResponse fromModel(Prospect prospect) {
        return new ProspectResponse(prospect.getId());
    }
}

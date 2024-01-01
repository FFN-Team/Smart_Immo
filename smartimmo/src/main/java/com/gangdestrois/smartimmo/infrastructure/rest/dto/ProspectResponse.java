package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import jakarta.validation.constraints.Email;

public record ProspectResponse(Long id, String firstName, String lastName, String completeName, @Email String email) {
    public static ProspectResponse fromModel(Prospect prospect) {
        return new ProspectResponse(prospect.id(),
                prospect.getFirstName(),
                prospect.getLastName(),
                prospect.getCompleteName(),
                prospect.getMail());
    }
}
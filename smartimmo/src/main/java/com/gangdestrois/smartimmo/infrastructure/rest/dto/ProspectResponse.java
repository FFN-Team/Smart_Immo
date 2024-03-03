package com.gangdestrois.smartimmo.infrastructure.rest.dto;

import com.gangdestrois.smartimmo.domain.prospect.ContactOrigin;
import com.gangdestrois.smartimmo.domain.prospect.Profession;
import com.gangdestrois.smartimmo.domain.prospect.Title;
import com.gangdestrois.smartimmo.domain.prospect.model.Home;
import com.gangdestrois.smartimmo.domain.prospect.model.Owner;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record ProspectResponse(Long id, String firstName, String lastName, String completeName, @Email String email,
                               ContactOrigin contactOrigin, Title title, LocalDate dateOfBirth, Profession profession,
                               Long mobile, Boolean authorizeContactOnSocialMedia, Home home, List<Owner> owners) {

    public static ProspectResponse fromModel(Prospect prospect) {
        return new ProspectResponse(prospect.id(),
                prospect.getFirstName(),
                prospect.getLastName(),
                prospect.getCompleteName(),
                prospect.getMail(),
                prospect.getContactOrigin(),
                prospect.getTitle(),
                prospect.getDateOfBirth(),
                prospect.getProfession(),
                prospect.getMobile(),
                prospect.authorizeContactOnSocialMedia(),
                prospect.getHome(),
                prospect.getOwners()
        );
    }
}
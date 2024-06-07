package com.gangdestrois.smartimmo.infrastructure.rest.dto.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gangdestrois.smartimmo.domain.filter.prospect.MathematicalComparator;
import com.gangdestrois.smartimmo.domain.filter.prospect.model.ProspectFilter;
import com.gangdestrois.smartimmo.domain.prospect.enums.ContactOrigin;
import com.gangdestrois.smartimmo.domain.prospect.enums.Profession;
import com.gangdestrois.smartimmo.domain.prospect.enums.Title;
import io.soabase.recordbuilder.core.RecordBuilder;

import static java.util.Objects.nonNull;

@JsonIgnoreProperties()
@RecordBuilder
public record ProspectFilterRequest
        (
                String prospectFilterName, ContactOrigin contactOrigin, Title title,
                MathematicalComparator ageComparator,
                Integer age, Profession profession, Boolean authorizeContactOnSocialMedia
        ) {
    public ProspectFilter toModel() {
        return new ProspectFilter(
                this.prospectFilterName, this.contactOrigin, this.title,
                this.ageComparator, this.age, this.profession,
                nonNull(this.authorizeContactOnSocialMedia) ? this.authorizeContactOnSocialMedia : null
        );
    }
}

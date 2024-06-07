package com.gangdestrois.smartimmo.infrastructure.rest.dto.Response;

import com.gangdestrois.smartimmo.domain.filter.prospect.enums.MathematicalComparator;
import com.gangdestrois.smartimmo.domain.filter.prospect.model.ProspectFilter;
import com.gangdestrois.smartimmo.domain.prospect.enums.ContactOrigin;
import com.gangdestrois.smartimmo.domain.prospect.enums.Profession;
import com.gangdestrois.smartimmo.domain.prospect.enums.Title;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record ProspectFilterResponse(Long id, String prospectFilterName, ContactOrigin contactOrigin, Title title,
                                     MathematicalComparator ageComparator, Integer age, Profession profession,
                                     Boolean authorizeContactOnSocialMedia) {
    public static ProspectFilterResponse fromModel(ProspectFilter prospectFilter) {
        return new ProspectFilterResponse(
                prospectFilter.getId(), prospectFilter.getProspectFilterName(), prospectFilter.getContactOrigin(),
                prospectFilter.getTitle(), prospectFilter.getAgeComparator(), prospectFilter.getAge(),
                prospectFilter.getProfession(), prospectFilter.isAuthorizeContactOnSocialMedia()
        );
    }
}

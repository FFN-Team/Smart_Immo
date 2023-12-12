package com.gangdestrois.smartimmo.domain.prospect;

import com.gangdestrois.smartimmo.domain.prospect.model.MaritalStatus;
import com.gangdestrois.smartimmo.domain.prospect.model.Owner;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.time.LocalDate;
import java.util.function.Predicate;

import static java.util.Objects.nonNull;

public class ExpandFamilyCriteria {
    public Predicate<Prospect> YOUNG_COUPLE_IN_COHABITATION_SECOND_YEAR = (Prospect prospect) ->
            nonNull(prospect.getHome()) &&
                    prospect.getHome().maritalStatus().equals(MaritalStatus.COHABITATION)
                    && prospect.getHome().children().size() == 0
                    && prospect.getOwners().stream()
                    .filter(Owner::isMain).anyMatch(owner -> owner.acquisitionDate().isBefore(LocalDate.now().minusYears(2)));

    public Predicate<Prospect> TEST = (Prospect prospect) -> true;
}

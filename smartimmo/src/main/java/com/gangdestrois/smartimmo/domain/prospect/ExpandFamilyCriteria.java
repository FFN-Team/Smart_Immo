package com.gangdestrois.smartimmo.domain.prospect;

import com.gangdestrois.smartimmo.domain.prospect.model.MaritalStatus;
import com.gangdestrois.smartimmo.domain.prospect.model.Owner;
import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.time.LocalDate;
import java.util.function.Predicate;

public class ExpandFamilyCriteria {
    public Predicate<Prospect> YOUNG_COUPLE_IN_COHABITATION_SECOND_YEAR = (Prospect prospect) -> {
        return prospect.getHome().maritalStatus().equals(MaritalStatus.COHABITATION)
                && prospect.getHome().children().size() == 0
                && prospect.getOwners().stream()
                .filter(Owner::isMain).anyMatch(owner -> owner.acquisitionDate().isBefore(LocalDate.now()));
    };

    public Predicate<Prospect> TEST = (Prospect prospect) -> {
        return true;
    };
}

package com.gangdestrois.smartimmo.domain.prospect;

import com.gangdestrois.smartimmo.domain.prospect.model.Prospect;

import java.util.function.Predicate;

public class PropectMayBuyBiggerHouseCriteria {
    private final ExpandFamilyCriteria expandFamilyCriteria = new ExpandFamilyCriteria();
    public Predicate<Prospect> combinedPredicate = expandFamilyCriteria.YOUNG_COUPLE_IN_COHABITATION_SECOND_YEAR.or(expandFamilyCriteria.TEST);
}

package com.gangdestrois.smartimmo.domain.salesHistory;

import com.gangdestrois.smartimmo.infrastructure.rest.error.BadRequestException;

import java.time.LocalDate;

public class Period {
    private final static LocalDate LOCAL_DATE_MIN = LocalDate.of(1900, 1, 1);
    private final static Long PERIOD_MAX_IN_YEARS = 100L;

    private static LocalDate getLocalDateMax(LocalDate startDate) {
        LocalDate potentialLocalDateMax = startDate.plusYears(PERIOD_MAX_IN_YEARS);
        if (potentialLocalDateMax.isAfter(LocalDate.now())) {
            return LocalDate.now();
        }
        return potentialLocalDateMax;
    }

    public static boolean endDateBeforeDateMax(LocalDate endDate, LocalDate dateMax) {
        return endDate.isBefore(dateMax) || endDate.isEqual(dateMax);
    }

    public static boolean isValid(LocalDate startDate, LocalDate endDate) {
        boolean startBeforeEnd = startDate.isBefore(endDate) || startDate.isEqual(endDate);
        boolean startAfterDateMin = startDate.isEqual(LOCAL_DATE_MIN) || startDate.isAfter(LOCAL_DATE_MIN);
        boolean periodInYearsValid = java.time.Period.between(startDate, endDate).getYears() <= PERIOD_MAX_IN_YEARS;
        LocalDate dateMax = getLocalDateMax(startDate);
        boolean endBeforeDateMax = endDateBeforeDateMax(endDate, dateMax);

        if (!startBeforeEnd) {
            throw new BadRequestException("The start date must be before the end date.");
        } else if (!startAfterDateMin) {
            throw new BadRequestException(String.format("The start date must be after %s.", LOCAL_DATE_MIN));
        } else if (!periodInYearsValid) {
            throw new BadRequestException(String.format("The period must be less than %d years.", PERIOD_MAX_IN_YEARS));
        } else if (!endBeforeDateMax) {
            throw new BadRequestException(String.format("The end date must be before %s.", dateMax));
        }
        return true;
    }
}

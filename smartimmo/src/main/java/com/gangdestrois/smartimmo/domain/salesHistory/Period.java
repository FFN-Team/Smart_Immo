package com.gangdestrois.smartimmo.domain.salesHistory;

import com.gangdestrois.smartimmo.infrastructure.rest.error.BadRequestException;
import com.gangdestrois.smartimmo.infrastructure.rest.error.ExceptionEnum;

import java.time.LocalDate;

public final class Period {
    private final static LocalDate LOCAL_DATE_MIN = LocalDate.of(1900, 1, 1);
    private final static Long PERIOD_MAX_IN_YEARS = 100L;

    private Period(){}

    private static LocalDate getLocalDateMax(LocalDate startDate) {
        LocalDate potentialLocalDateMax = startDate.plusYears(PERIOD_MAX_IN_YEARS);
        if (potentialLocalDateMax.isAfter(LocalDate.now())) {
            return LocalDate.now();
        }
        return potentialLocalDateMax;
    }

    private static boolean endDateBeforeDateMax(LocalDate startDate, LocalDate endDate) {
        LocalDate dateMax = getLocalDateMax(startDate);
        return endDate.isBefore(dateMax) || endDate.isEqual(dateMax);
    }

    private static boolean startBeforeEnd(LocalDate startDate, LocalDate endDate) {
        return startDate.isBefore(endDate) || startDate.isEqual(endDate);
    }

    private static boolean startAfterDateMin(LocalDate startDate) {
        return startDate.isEqual(LOCAL_DATE_MIN) || startDate.isAfter(LOCAL_DATE_MIN);
    }

    private static boolean periodInYearsValid(LocalDate startDate, LocalDate endDate) {
        return java.time.Period.between(startDate, endDate).getYears() <= PERIOD_MAX_IN_YEARS;
    }

    public static boolean isValid(LocalDate startDate, LocalDate endDate) {
        if (!startBeforeEnd(startDate, endDate)) throw new BadRequestException(ExceptionEnum.BAD_REQUEST,
                "The start date must be before the end date.");

        if (!startAfterDateMin(startDate)) throw new BadRequestException(ExceptionEnum.BAD_REQUEST,
                String.format("The start date must be after %s.", LOCAL_DATE_MIN));

        if (!periodInYearsValid(startDate, endDate)) throw new BadRequestException(ExceptionEnum.BAD_REQUEST,
                String.format("The period must be less than %d years.", PERIOD_MAX_IN_YEARS));

        if (!endDateBeforeDateMax(startDate, endDate)) throw new BadRequestException(ExceptionEnum.BAD_REQUEST,
                String.format("The end date must be before %s.", getLocalDateMax(startDate)));

        return true;
    }
}

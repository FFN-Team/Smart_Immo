package com.gangdestrois.smartimmo.domain.salesHistory;

import org.springframework.data.util.Optionals;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.time.Period.between;

public final class SalesHistoryPeriodUtils {
    private final static LocalDate LOCAL_DATE_MIN = LocalDate.of(1900, 1, 1);
    private final static Integer PERIOD_MAX_IN_YEARS = 100;

    private SalesHistoryPeriodUtils() {
    }

    private static LocalDate getDateMax(LocalDate startDate) {
        var potentialLocalDateMax = startDate.plusYears(PERIOD_MAX_IN_YEARS);
        if (potentialLocalDateMax.isAfter(LocalDate.now())) {
            return LocalDate.now();
        }
        return potentialLocalDateMax;
    }

    private static Optional<String> endDateBeforeDateMax(LocalDate startDate, LocalDate endDate) {
        LocalDate dateMax = getDateMax(startDate);
        return (endDate.isBefore(dateMax) || endDate.isEqual(dateMax)) ?
                Optional.of(String.format("The end date must be before %s.", getDateMax(startDate))) : Optional.empty();
    }

    private static Optional<String> startBeforeEnd(LocalDate startDate, LocalDate endDate) {
        return (startDate.isBefore(endDate) || startDate.isEqual(endDate)) ?
                Optional.of("The start date must be before the end date.") : Optional.empty();
    }

    private static Optional<String> startAfterDateMin(LocalDate startDate) {
        return (startDate.isEqual(LOCAL_DATE_MIN) || startDate.isAfter(LOCAL_DATE_MIN)) ?
                Optional.of(String.format("The start date must be after %s.", LOCAL_DATE_MIN)) : Optional.empty();
    }

    private static Optional<String> periodInYearsValid(LocalDate startDate, LocalDate endDate) {
        return (between(startDate, endDate).getYears() <= PERIOD_MAX_IN_YEARS) ?
                Optional.of(String.format("The period must be less than %d years.", PERIOD_MAX_IN_YEARS)) : Optional.empty();
    }

    public static List<String> findValidationsErrors(LocalDate startDate, LocalDate endDate) {
        return Optionals.toStream(
                endDateBeforeDateMax(startDate, endDate),
                startBeforeEnd(startDate, endDate),
                startAfterDateMin(startDate),
                periodInYearsValid(startDate, endDate)
        ).toList();
    }
}

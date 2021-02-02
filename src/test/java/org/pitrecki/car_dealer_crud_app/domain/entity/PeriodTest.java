package org.pitrecki.car_dealer_crud_app.domain.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PeriodTest {

    private static final LocalDate SOME_START_DATE = LocalDate.of(2001, 1, 1);
    private static final LocalDate SOME_END_DATE = LocalDate.of(2021, 10, 15);

    @ParameterizedTest
    @MethodSource("yearsParams")
    void shouldCreatePeriodUsingConstructorAndCorrectReturnsDates(LocalDate startDate, LocalDate endDate) {
        Period year = new Period(startDate, endDate);

        assertThat(year)
                .extracting(Period::getStartDate, Period::getEndDate)
                .containsExactly(startDate, endDate);
    }

    private static Stream<Arguments> yearsParams() {
        return Stream.of(
                Arguments.of(SOME_START_DATE, SOME_END_DATE),
                Arguments.of(SOME_START_DATE, SOME_START_DATE),
                Arguments.of(SOME_END_DATE, SOME_END_DATE)
        );
    }

    @Test
    void shouldThrowsExceptionWhenStarDateIsGreatThenEndDate() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Period(SOME_END_DATE, SOME_START_DATE))
                .withMessage("Start date cannot be greater than end date");
    }
}

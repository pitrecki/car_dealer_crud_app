package org.pitrecki.car_dealer_crud_app.domain.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.pitrecki.car_dealer_crud_app.domain.entity.Car.CarBuilder.aCar;

class CarTest {

    private static final String SOME_MODEL = "someMode";
    private static final String SOME_MAKE = "someMake";
    private static final List<ServiceTicket> SOME_SERVICE_TICKETS = of(new ServiceTicket());
    private static final List<Part> SOME_PARTS = of(new Part());
    private static final LocalDate SOME_DATE = LocalDate.now();
    private static final Period SOME_PERIOD = new Period(SOME_DATE, SOME_DATE.plusDays(1));

    @Test
    void shouldCreateCarEntityAndReturnExpectedValues() {
        Car car = aCar()
                .withMake(SOME_MAKE)
                .withModel(SOME_MODEL)
                .withServiceTickets(SOME_SERVICE_TICKETS)
                .withPeriod(SOME_PERIOD)
                .withParts(SOME_PARTS)
                .build();

        assertThat(car)
                .extracting(Car::getMake, Car::getModel, Car::getParts, Car::getPeriod, Car::getServiceTickets)
                .containsExactly(SOME_MAKE, SOME_MODEL, SOME_PARTS, SOME_PERIOD, SOME_SERVICE_TICKETS);
    }
}

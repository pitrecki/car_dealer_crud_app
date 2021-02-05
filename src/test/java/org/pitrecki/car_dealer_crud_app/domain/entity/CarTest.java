package org.pitrecki.car_dealer_crud_app.domain.entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.pitrecki.car_dealer_crud_app.domain.entity.Car.CarBuilder.aCar;
import static org.pitrecki.car_dealer_crud_app.utils.TestDummies.DUMMY_INTERGER;
import static org.pitrecki.car_dealer_crud_app.utils.TestDummies.DUMMY_PART;
import static org.pitrecki.car_dealer_crud_app.utils.TestDummies.DUMMY_SERVICE_TICKET;
import static org.pitrecki.car_dealer_crud_app.utils.TestDummies.DUMMY_STRING;

class CarTest {

    private static final List<Part> DUMMY_PARTS = List.of(DUMMY_PART);
    private static final List<ServiceTicket> DUMMY_SERVICE_TICKETS = List.of(DUMMY_SERVICE_TICKET);

    @Test
    void shouldCreateCarEntityAndReturnExpectedValues() {
        Car car = aCar()
                .withMake(DUMMY_STRING)
                .withModel(DUMMY_STRING)
                .withServiceTickets(DUMMY_SERVICE_TICKETS)
                .withStartYear(DUMMY_INTERGER)
                .withEndYear(DUMMY_INTERGER)
                .withParts(DUMMY_PARTS)
                .build();

        assertThat(car)
                .extracting(Car::getMake, Car::getModel, Car::getParts, Car::getStartYear,
                        Car::getEndYear, Car::getServiceTickets)
                .containsExactly(DUMMY_STRING, DUMMY_STRING, DUMMY_PARTS, DUMMY_INTERGER,
                        DUMMY_INTERGER, DUMMY_SERVICE_TICKETS);
    }
}

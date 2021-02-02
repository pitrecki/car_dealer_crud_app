package org.pitrecki.car_dealer_crud_app.domain.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.pitrecki.car_dealer_crud_app.domain.entity.ServiceTicket.ServiceTicketBuilder.aServiceTicket;

class ServiceTicketTest {

    public static final Car SOME_CAR = new Car();
    public static final LocalDate SOME_DATE = LocalDate.now();
    public static final Period SOME_PERIOD = new Period(SOME_DATE, SOME_DATE.plusDays(1));
    public static final String SOME_DESCRIPTION = "someDescription";

    @Test
    void shouldCreateServiceTicketAndReturnExpectedValues() {
        ServiceTicket serviceTicket = aServiceTicket()
                .withCar(SOME_CAR)
                .withPeriod(SOME_PERIOD)
                .withDescription(SOME_DESCRIPTION)
                .build();

        assertThat(serviceTicket)
                .extracting(ServiceTicket::getCar, ServiceTicket::getDescription, ServiceTicket::getPeriod)
                .containsExactly(SOME_CAR, SOME_DESCRIPTION, SOME_PERIOD);
    }
}

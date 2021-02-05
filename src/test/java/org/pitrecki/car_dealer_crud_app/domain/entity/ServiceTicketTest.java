package org.pitrecki.car_dealer_crud_app.domain.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.pitrecki.car_dealer_crud_app.domain.entity.ServiceTicket.ServiceTicketBuilder.aServiceTicket;
import static org.pitrecki.car_dealer_crud_app.utils.TestDummies.DUMMY_CAR;
import static org.pitrecki.car_dealer_crud_app.utils.TestDummies.DUMMY_DATE;
import static org.pitrecki.car_dealer_crud_app.utils.TestDummies.DUMMY_STRING;

class ServiceTicketTest {

    @Test
    void shouldCreateServiceTicketAndReturnExpectedValues() {
        ServiceTicket serviceTicket = aServiceTicket()
                .withCar(DUMMY_CAR)
                .withEndDate(DUMMY_DATE)
                .withDescription(DUMMY_STRING)
                .build();

        assertThat(serviceTicket)
                .extracting(ServiceTicket::getCar, ServiceTicket::getDescription, ServiceTicket::getEndDate)
                .containsExactly(DUMMY_CAR, DUMMY_STRING, DUMMY_DATE);
    }
}

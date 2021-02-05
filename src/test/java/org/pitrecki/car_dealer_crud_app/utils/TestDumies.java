package org.pitrecki.car_dealer_crud_app.utils;

import org.pitrecki.car_dealer_crud_app.domain.entity.Car;
import org.pitrecki.car_dealer_crud_app.domain.entity.Part;
import org.pitrecki.car_dealer_crud_app.domain.entity.Period;
import org.pitrecki.car_dealer_crud_app.domain.entity.ServiceTicket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.pitrecki.car_dealer_crud_app.domain.entity.Car.CarBuilder.aCar;
import static org.pitrecki.car_dealer_crud_app.domain.entity.Part.PartBuilder.aPart;
import static org.pitrecki.car_dealer_crud_app.domain.entity.ServiceTicket.ServiceTicketBuilder.aServiceTicket;

public class TestDumies {

    public static final LocalDate DUMMY_DATE = LocalDate.now();
    public static final String DUMMY_STRING = "dummyString";
    public static final BigDecimal DUMMY_PRICE = new BigDecimal("10");
    public static final boolean DUMMY_BOOLEAN = true;
    public static final Integer DUMMY_INTERGER = 3;
    public static final Period DUMMY_PERIOD = new Period(DUMMY_DATE, DUMMY_DATE.plusDays(1));
    public static final Long DUMMY_ID = 1L;

    public static final Part DUMMY_PART = aPart()
            .withDescription(DUMMY_STRING)
            .withPrice(DUMMY_PRICE)
            .withAvalaibility(DUMMY_BOOLEAN)
            .withDaysToDispatch(DUMMY_INTERGER)
            .withName(DUMMY_STRING)
            .build();
    public static final ServiceTicket DUMMY_SERVICE_TICKET = aServiceTicket()
            .withDescription(DUMMY_STRING)
            .withPeriod(DUMMY_PERIOD)
            .build();
    public static final Car DUMMY_CAR = aCar()
            .withModel(DUMMY_STRING)
            .withPeriod(DUMMY_PERIOD)
            .withMake(DUMMY_STRING)
            .build();

    static {
        DUMMY_CAR.setId(DUMMY_ID);
        DUMMY_PART.setId(DUMMY_ID);
        DUMMY_SERVICE_TICKET.setId(DUMMY_ID);
        DUMMY_CAR.setParts(List.of(DUMMY_PART));
        DUMMY_CAR.setServiceTickets(List.of(DUMMY_SERVICE_TICKET));
    }

    private TestDumies() {
    }

}

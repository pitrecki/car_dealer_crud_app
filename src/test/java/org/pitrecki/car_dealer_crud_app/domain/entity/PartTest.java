package org.pitrecki.car_dealer_crud_app.domain.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.pitrecki.car_dealer_crud_app.domain.entity.Part.aPart;

class PartTest {

    public static final Car SOME_CAR = new Car();
    public static final String SOME_NAME = "someName";
    public static final int SOME_DAYS_TO_DISPATCH = 1;
    public static final BigDecimal SOME_PRICE = new BigDecimal(1);
    public static final String SOME_DESCRIPTION = "someDescription";
    public static final boolean SOME_AVALAIBILITY = true;

    @Test
    void shouldCreatePartAndReturnExpctedValues() {
        Part part = aPart()
                .withCar(SOME_CAR)
                .withName(SOME_NAME)
                .withPrice(SOME_PRICE)
                .withDaysToDispatch(SOME_DAYS_TO_DISPATCH)
                .withDescription(SOME_DESCRIPTION)
                .withIsAvailable(SOME_AVALAIBILITY)
                .build();

        assertThat(part)
                .extracting(Part::getCar, Part::getDaysToDispatch, Part::getDescription, Part::getName,
                        Part::getPrice, Part::getIsAvailable)
                .containsExactly(SOME_CAR, SOME_DAYS_TO_DISPATCH, SOME_DESCRIPTION, SOME_NAME, SOME_PRICE, SOME_AVALAIBILITY);
    }
}

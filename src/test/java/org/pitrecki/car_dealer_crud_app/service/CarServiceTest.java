package org.pitrecki.car_dealer_crud_app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pitrecki.car_dealer_crud_app.domain.adapter.DBCarAdapter;
import org.pitrecki.car_dealer_crud_app.domain.entity.Part;
import org.pitrecki.car_dealer_crud_app.domain.model.CarDto;

import java.util.stream.Stream;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_CAR;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_PART;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_STRING;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    private static final CarDto CAR = new CarDto(DUMMY_STRING, DUMMY_STRING);

    @Mock private DBCarAdapter carAdapter;

    @InjectMocks private CarService service;


    @Test
    void shouldReturnsCarsByPartsName() {
        given(carAdapter.findCarByModelAndMake(DUMMY_STRING, DUMMY_STRING)).willReturn(of(DUMMY_CAR));

        Stream<Part> result = service.findPartsByName(DUMMY_STRING, CAR);

        assertThat(result).contains(DUMMY_PART);
    }

    @Test
    void shouldReturnsEmptyStreamWhenGivenCarNotExits() {
        given(carAdapter.findCarByModelAndMake(DUMMY_STRING, DUMMY_STRING)).willReturn(empty());

        Stream<Part> result = service.findPartsByName(DUMMY_STRING, CAR);

        assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnsCarsByPartDescription() {
        given(carAdapter.findCarByModelAndMake(DUMMY_STRING, DUMMY_STRING)).willReturn(of(DUMMY_CAR));

        Stream<Part> result = service.findPartsByDescription(DUMMY_STRING, CAR);

        assertThat(result).contains(DUMMY_PART);
    }
}

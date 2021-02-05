package org.pitrecki.car_dealer_crud_app.utils;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.pitrecki.car_dealer_crud_app.domain.model.CarDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.pitrecki.car_dealer_crud_app.utils.TestDummies.DUMMY_CAR;
import static org.pitrecki.car_dealer_crud_app.utils.TestDummies.DUMMY_STRING;

class ObjectMapperTest {

    private final ObjectMapper mapper = new ObjectMapper(new ModelMapper());

    @Test
    void shouldConvertEntityToDto() {
        CarDto result = mapper.mapToDto(DUMMY_CAR, CarDto.class);
        assertThat(result)
                .extracting(CarDto::getMake, CarDto::getModel)
                .containsExactly(DUMMY_STRING, DUMMY_STRING);
    }
}
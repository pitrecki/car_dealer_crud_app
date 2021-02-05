package org.pitrecki.car_dealer_crud_app.domain.adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pitrecki.car_dealer_crud_app.domain.entity.Car;
import org.pitrecki.car_dealer_crud_app.domain.repository.CarRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DBCarAdapterTest {

    private static final long SOME_ID = 1L;
    private static final Car SOME_CAR = new Car();

    @Mock private CarRepository repository;
    @InjectMocks private DBCarAdapter adapter;

    @Test
    void shouldFindCarByGivenId() {
        given(repository.findById(SOME_ID)).willReturn(Optional.of(SOME_CAR));

        Optional<Car> result = adapter.findById(SOME_ID);

        assertThat(result).contains(SOME_CAR);
    }
}

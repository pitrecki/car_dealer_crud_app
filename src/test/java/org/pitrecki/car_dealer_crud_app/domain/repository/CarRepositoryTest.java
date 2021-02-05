package org.pitrecki.car_dealer_crud_app.domain.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.pitrecki.car_dealer_crud_app.domain.entity.Car;
import org.pitrecki.car_dealer_crud_app.domain.entity.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_CAR;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_STRING;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql(value = "classpath:sql/car_test_data.sql")
class CarRepositoryTest {

    @Autowired private CarRepository repository;

    @Test
    void shouldFindCarByGivenNameAndMake() {
        Optional<Car> result = repository.findByModelAndMake(DUMMY_STRING, DUMMY_STRING);

        assertThat(result)
                .usingRecursiveComparison()
                .ignoringFieldsOfTypes(Period.class)
                .isEqualTo(Optional.of(DUMMY_CAR));
    }
}

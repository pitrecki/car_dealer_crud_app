package org.pitrecki.car_dealer_crud_app.domain.repository;

import org.pitrecki.car_dealer_crud_app.domain.entity.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Long> {

    Optional<Car> findByModelAndMake(String name, String make);
}

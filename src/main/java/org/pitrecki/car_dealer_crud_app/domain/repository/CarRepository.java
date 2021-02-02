package org.pitrecki.car_dealer_crud_app.domain.repository;

import org.pitrecki.car_dealer_crud_app.domain.entity.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}

package org.pitrecki.car_dealer_crud_app.domain.adapter;

import lombok.AllArgsConstructor;
import org.pitrecki.car_dealer_crud_app.domain.entity.Car;
import org.pitrecki.car_dealer_crud_app.domain.repository.CarRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class DBCarAdapter {

    private final CarRepository repository;

    public Optional<Car> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<Car> findCarByModelAndMake(String model, String make) {
        return repository.findByModelAndMake(model, make);
    }
}

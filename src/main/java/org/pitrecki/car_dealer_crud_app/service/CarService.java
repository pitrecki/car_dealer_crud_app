package org.pitrecki.car_dealer_crud_app.service;

import lombok.AllArgsConstructor;
import org.pitrecki.car_dealer_crud_app.aspect.Logging;
import org.pitrecki.car_dealer_crud_app.domain.adapter.DBCarAdapter;
import org.pitrecki.car_dealer_crud_app.domain.entity.Car;
import org.pitrecki.car_dealer_crud_app.domain.entity.Part;
import org.pitrecki.car_dealer_crud_app.domain.model.CarDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

@Service
@Transactional
@AllArgsConstructor
public class CarService {
    private final DBCarAdapter carAdapter;

    @Logging
    public Stream<Part> findPartsByName(String name, CarDto car) {
        return findCarAndReturnAllParts(car).stream()
                .filter(part -> part.getName().contains(name) && notBlankOrEmpty(name))
                .distinct();
    }

    @Logging
    public Stream<Part> findPartsByDescription(String description, CarDto car) {
        return findCarAndReturnAllParts(car).stream()
                .filter(part -> part.getDescription().contains(description) && notBlankOrEmpty(description))
                .distinct();
    }


    private List<Part> findCarAndReturnAllParts(CarDto car) {
        return carAdapter.findCarByModelAndMake(car.getModel(), car.getMake())
                .map(Car::getParts)
                .orElse(emptyList());
    }

    private static boolean notBlankOrEmpty(String  s) {
        return !(s.isEmpty() || s.isBlank());
    }
}

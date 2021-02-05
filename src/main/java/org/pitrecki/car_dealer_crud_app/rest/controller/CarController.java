package org.pitrecki.car_dealer_crud_app.rest.controller;

import lombok.AllArgsConstructor;
import org.pitrecki.car_dealer_crud_app.domain.entity.Part;
import org.pitrecki.car_dealer_crud_app.domain.model.CarDto;
import org.pitrecki.car_dealer_crud_app.domain.model.PartDto;
import org.pitrecki.car_dealer_crud_app.service.CarService;
import org.pitrecki.car_dealer_crud_app.utils.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@AllArgsConstructor
public class CarController {
    private final CarService service;
    private final ObjectMapper mapper;

    @GetMapping(value = "/api/car", params = "partName")
    public ResponseEntity<Stream<PartDto>> searchPartsByName(@RequestParam(name = "partName") String name, CarDto carDto) {
        Stream<Part> parts = service.findPartsByName(name, carDto);
        return prepareResponse(parts, mapper);
    }

    @GetMapping(value = "/api/car", params = "partDesc")
    public ResponseEntity<Stream<PartDto>> searchPartsByDescrrption(@RequestParam(name = "partDesc") String description,
                                                                    CarDto carDto) {
        Stream<Part> parts = service.findPartsByDescription(description, carDto);
        return prepareResponse(parts, mapper);
    }

    private static ResponseEntity<Stream<PartDto>> prepareResponse(Stream<Part> parts, ObjectMapper mapper) {
        Stream<PartDto> response = parts.map(part -> mapper.mapToDto(part, PartDto.class));
        return ResponseEntity.ok(response);
    }
}

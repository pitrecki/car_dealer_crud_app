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

import javax.validation.Valid;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
public class CarController {
    private final CarService service;
    private final ObjectMapper mapper;

    @GetMapping(value = "/api/car")
    public ResponseEntity<Stream<PartDto>> searchPartsByNameOrDescription(@RequestParam(name = "partName", required = false) String name,
                                                                          @RequestParam(name = "partDesc", required = false) String description,
                                                                          @Valid CarDto carDto) {
        Stream<Part> parts = description == null ?
                service.findPartsByName(name, carDto) :
                service.findPartsByDescription(description, carDto);

        return prepareResponse(parts, mapper);
    }

    private static ResponseEntity<Stream<PartDto>> prepareResponse(Stream<Part> parts, ObjectMapper mapper) {
        Stream<PartDto> response = parts.map(part -> mapper.mapToDto(part, PartDto.class));
        return ResponseEntity.ok(response);
    }
}

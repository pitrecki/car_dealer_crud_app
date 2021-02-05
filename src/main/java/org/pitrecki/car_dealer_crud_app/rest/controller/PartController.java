package org.pitrecki.car_dealer_crud_app.rest.controller;

import lombok.RequiredArgsConstructor;
import org.pitrecki.car_dealer_crud_app.domain.entity.Part;
import org.pitrecki.car_dealer_crud_app.domain.model.PartAvailabilityDto;
import org.pitrecki.car_dealer_crud_app.service.PartService;
import org.pitrecki.car_dealer_crud_app.utils.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PartController {

    private final PartService service;
    private final ObjectMapper mapper;

    @PutMapping("/api/part/{id}")
    public void updateDescription(@PathVariable(name = "id") Long id, @RequestParam("desc") String description) {
        service.updatePartDescription(id, description);
    }

    @GetMapping("api/part/{id}")
    public ResponseEntity<PartAvailabilityDto> findPartAvailibity(@PathVariable(name = "id") Long id) {
        Part part = service.findPartAvailability(id);
        return ResponseEntity.ok(mapper.mapToDto(part, PartAvailabilityDto.class));
    }
}

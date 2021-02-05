package org.pitrecki.car_dealer_crud_app.rest.controller;

import lombok.RequiredArgsConstructor;
import org.pitrecki.car_dealer_crud_app.service.PartService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PartController {

    private final PartService service;

    @PutMapping("/api/part/{id}")
    public void updateDescription(@PathVariable(name = "id") Long id, @RequestParam("desc") String description) {
        service.updatePartDescription(id, description);
    }
}

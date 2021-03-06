package org.pitrecki.car_dealer_crud_app.rest.controller;

import lombok.AllArgsConstructor;
import org.pitrecki.car_dealer_crud_app.domain.entity.ServiceTicket;
import org.pitrecki.car_dealer_crud_app.domain.model.CarDto;
import org.pitrecki.car_dealer_crud_app.domain.model.ServiceTicketDto;
import org.pitrecki.car_dealer_crud_app.domain.model.ServiceTicketRequest;
import org.pitrecki.car_dealer_crud_app.domain.model.TimespanRequest;
import org.pitrecki.car_dealer_crud_app.service.ServiceTicketService;
import org.pitrecki.car_dealer_crud_app.utils.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
public class ServiceTicketController {
    private final ServiceTicketService service;
    private final ObjectMapper mapper;

    @GetMapping("/api/service")
    public ResponseEntity<Stream<ServiceTicketDto>> findAllServiceTicketForCarWithinPeriod(@Valid CarDto carDto,
                                                                                           TimespanRequest timespan) {
        Stream<ServiceTicket> tickets = service.findAllServiceTicketsWithinDate(carDto, timespan);
        Stream<ServiceTicketDto> stream = tickets.map(serviceTicket -> mapper.mapToDto(serviceTicket, ServiceTicketDto.class));
        return ResponseEntity.ok(stream);
    }

    @PostMapping("/api/service")
    public void addTicket(@RequestBody @Valid ServiceTicketRequest request) {
        service.addTicket(request);
    }
}

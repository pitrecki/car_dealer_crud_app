package org.pitrecki.car_dealer_crud_app.service;

import lombok.AllArgsConstructor;
import org.pitrecki.car_dealer_crud_app.domain.adapter.DBCarAdapter;
import org.pitrecki.car_dealer_crud_app.domain.entity.Car;
import org.pitrecki.car_dealer_crud_app.domain.entity.ServiceTicket;
import org.pitrecki.car_dealer_crud_app.domain.model.CarDto;
import org.pitrecki.car_dealer_crud_app.domain.model.TimespanRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static org.threeten.extra.LocalDateRange.of;

@Service
@Transactional
@AllArgsConstructor
public class ServiceTicketService {
    private final DBCarAdapter carAdapter;

    public Stream<ServiceTicket> findAllServiceTicketsWithinDate(CarDto car, TimespanRequest period) {
        return findCarAndReturnAllServiceTickets(car).stream()
                .filter(serviceTicket -> compareDates(serviceTicket, period));
    }

    private List<ServiceTicket> findCarAndReturnAllServiceTickets(CarDto car) {
        return carAdapter.findCarByModelAndMake(car.getModel(), car.getMake())
                .map(Car::getServiceTickets)
                .orElse(emptyList());
    }

    private static boolean compareDates(ServiceTicket ticket, TimespanRequest timespan) {
        return
            of(timespan.getStartDate(), timespan.getEndDate())
            .isConnected(of(ticket.getStartDate(), ticket.getEndDate()));
    }
}

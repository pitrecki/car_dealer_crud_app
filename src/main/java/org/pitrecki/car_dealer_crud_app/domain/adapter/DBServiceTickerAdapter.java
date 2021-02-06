package org.pitrecki.car_dealer_crud_app.domain.adapter;

import lombok.AllArgsConstructor;
import org.pitrecki.car_dealer_crud_app.domain.entity.ServiceTicket;
import org.pitrecki.car_dealer_crud_app.domain.repository.ServiceTicketRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DBServiceTickerAdapter {
    private final ServiceTicketRepository repository;

    public void addTicket(ServiceTicket ticket) {
        repository.save(ticket);
    }
}

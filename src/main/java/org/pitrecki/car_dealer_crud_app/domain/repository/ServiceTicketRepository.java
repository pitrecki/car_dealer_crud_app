package org.pitrecki.car_dealer_crud_app.domain.repository;

import org.pitrecki.car_dealer_crud_app.domain.entity.ServiceTicket;
import org.springframework.data.repository.CrudRepository;

public interface ServiceTicketRepository extends CrudRepository<ServiceTicket, Long> {
}

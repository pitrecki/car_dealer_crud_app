package org.pitrecki.car_dealer_crud_app.domain.repository;

import org.pitrecki.car_dealer_crud_app.domain.entity.Part;
import org.springframework.data.repository.CrudRepository;

public interface PartRepository extends CrudRepository<Part, Long> {
}

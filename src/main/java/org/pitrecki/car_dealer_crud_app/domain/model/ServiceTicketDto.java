package org.pitrecki.car_dealer_crud_app.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ServiceTicketDto {
    private CarDto car;
    private String description;
    private PeriodDto period;

}

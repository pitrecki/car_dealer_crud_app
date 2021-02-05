package org.pitrecki.car_dealer_crud_app.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PartAvailabilityDto {
    private Integer daysToDispatch;
    private Boolean isAvailable;
}

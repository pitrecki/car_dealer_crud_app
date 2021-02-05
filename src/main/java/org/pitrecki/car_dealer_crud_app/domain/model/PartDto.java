package org.pitrecki.car_dealer_crud_app.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PartDto {
    private String description;
    private String name;
    private BigDecimal price;
    private Integer daysToDispatch;
    private Boolean isAvailable;
}

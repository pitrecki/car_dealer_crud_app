package org.pitrecki.car_dealer_crud_app.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceTicketRequest {
    @NotNull private Long id;
    @NotNull private String description;
}

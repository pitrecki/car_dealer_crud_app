package org.pitrecki.car_dealer_crud_app.domain.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import static javax.persistence.GenerationType.SEQUENCE;

@MappedSuperclass
@NoArgsConstructor
@Data
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    @NonNull
    private Long id;
}

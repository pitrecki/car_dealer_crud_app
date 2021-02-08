package org.pitrecki.car_dealer_crud_app.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "car")
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"parts", "serviceTickets"})
@ToString(exclude = {"parts", "serviceTickets"})
@Builder(setterPrefix = "with", builderMethodName = "aCar")
public class Car extends BaseEntity {

    @NotNull @Column(name = "model", nullable = false, unique = true) private String model;
    @NotNull @Column(name = "make", nullable = false) private String make;
    @NotNull @Column(name = "production_start_date", nullable = false) private Integer startYear;
    @NotNull @Column(name = "production_end_date", nullable = false) private Integer endYear;
    @Builder.Default
    @OneToMany(mappedBy = "car", orphanRemoval = true) private List<ServiceTicket> serviceTickets = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "car", orphanRemoval = true) private List<Part> parts = new ArrayList<>();

    public Car(@NotNull String model, @NotNull String make, @NotNull Integer startYear, @NotNull Integer endYear) {
        this.model = model;
        this.make = make;
        this.startYear = startYear;
        this.endYear = endYear;
    }
}




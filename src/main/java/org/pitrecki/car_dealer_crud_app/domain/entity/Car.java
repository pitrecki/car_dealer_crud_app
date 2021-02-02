package org.pitrecki.car_dealer_crud_app.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Car extends BaseEntity {

    @NotNull @Column(name = "model", nullable = false, unique = true) private String model;
    @NotNull @Column(name = "make", nullable = false) private String make;

    @AttributeOverrides({
            @AttributeOverride(name = "startDate", column = @Column(name = "production_start_date", nullable = false)),
            @AttributeOverride(name = "endDate", column = @Column(name = "production_end_date", nullable = false)),
    })
    private Period period;

    @OneToMany(mappedBy = "car", orphanRemoval = true) private List<ServiceTicket> serviceTickets = new ArrayList<>();
    @OneToMany(mappedBy = "car", orphanRemoval = true) private List<Part> parts = new ArrayList<>();

    public Car(@NotNull String model, @NotNull String make, Period period) {
        this.model = model;
        this.make = make;
        this.period = period;
    }


    public static final class CarBuilder {
        private Car car;

        private CarBuilder() {
            car = new Car();
        }

        public static CarBuilder aCar() {
            return new CarBuilder();
        }

        public CarBuilder withModel(String model) {
            car.setModel(model);
            return this;
        }

        public CarBuilder withMake(String make) {
            car.setMake(make);
            return this;
        }

        public CarBuilder withPeriod(Period period) {
            car.setPeriod(period);
            return this;
        }

        public CarBuilder withServiceTickets(List<ServiceTicket> serviceTickets) {
            car.setServiceTickets(serviceTickets);
            return this;
        }

        public CarBuilder withParts(List<Part> parts) {
            car.setParts(parts);
            return this;
        }

        public Car build() {
            return car;
        }
    }
}




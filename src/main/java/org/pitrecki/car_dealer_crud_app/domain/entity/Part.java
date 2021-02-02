package org.pitrecki.car_dealer_crud_app.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "part")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Part extends BaseEntity {
    @NotNull @Column(name = "name", nullable = false) private String name;
    @NotNull @Column(name = "description", nullable = false) private String description;
    @NotNull @Column(name = "price", nullable = false) private BigDecimal price;
    @Column(name = "available", nullable = false) private boolean isAvailable;
    @Positive @Column(name = "days_to_dispatch", nullable = false) private int daysToDispatch;
    @ManyToOne @JoinColumn(name = "car_id", nullable = false) private Car car;

    public Part(@NotNull String name, @NotNull String description, @NotNull BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }


    public static final class PartBuilder {
        private Part part;

        private PartBuilder() {
            part = new Part();
        }

        public static PartBuilder aPart() {
            return new PartBuilder();
        }

        public PartBuilder withName(String name) {
            part.setName(name);
            return this;
        }

        public PartBuilder withDescription(String description) {
            part.setDescription(description);
            return this;
        }

        public PartBuilder withPrice(BigDecimal price) {
            part.setPrice(price);
            return this;
        }

        public PartBuilder withAvalaibility(boolean avalaibility) {
            part.setAvailable(avalaibility);
            return this;
        }

        public PartBuilder withDaysToDispatch(int daysToDispatch) {
            part.setDaysToDispatch(daysToDispatch);
            return this;
        }

        public PartBuilder withCar(Car car) {
            part.setCar(car);
            return this;
        }

        public Part build() {
            return part;
        }
    }
}

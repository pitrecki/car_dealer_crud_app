package org.pitrecki.car_dealer_crud_app.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "service_ticket")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceTicket extends BaseEntity {
    @NotNull @Column(name = "description", nullable = false) private String description;

    @ManyToOne @JoinColumn(name = "car_id", nullable = false) private Car car;

    @AttributeOverrides({
            @AttributeOverride(name = "startDate", column = @Column(name = "service_start_date", nullable = false)),
            @AttributeOverride(name = "endDate", column = @Column(name = "service_end_date", nullable = false)),
    })
    private Period period;

    public ServiceTicket(@NotNull String description, Period period) {
        this.description = description;
        this.period = period;
    }


    public static final class ServiceTicketBuilder {
        private ServiceTicket serviceTicket;

        private ServiceTicketBuilder() {
            serviceTicket = new ServiceTicket();
        }

        public static ServiceTicketBuilder aServiceTicket() {
            return new ServiceTicketBuilder();
        }

        public ServiceTicketBuilder withDescription(String description) {
            serviceTicket.setDescription(description);
            return this;
        }

        public ServiceTicketBuilder withCar(Car car) {
            serviceTicket.setCar(car);
            return this;
        }

        public ServiceTicketBuilder withPeriod(Period period) {
            serviceTicket.setPeriod(period);
            return this;
        }

        public ServiceTicket build() {
            return serviceTicket;
        }
    }
}

package org.pitrecki.car_dealer_crud_app.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "service_ticket")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceTicket extends BaseEntity {
    @NotNull @Column(name = "description", nullable = false) private String description;

    @ManyToOne @JoinColumn(name = "car_id", nullable = false) private Car car;

    @NotNull @Column(name = "start_date", nullable = false) private LocalDate startDate = LocalDate.now();
    @FutureOrPresent @Column(name = "end_date") private LocalDate endDate;
    
    @ManyToMany(mappedBy = "tickets")
    private List<Part> parts;

    public ServiceTicket(@NotNull String description) {
        this.description = description;
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

        public ServiceTicketBuilder withEndDate(LocalDate endDate) {
            serviceTicket.setEndDate(endDate);
            return this;
        }

        public ServiceTicketBuilder withParts(List<Part> parts) {
            serviceTicket.setParts(parts);
            return this;
        }

        public ServiceTicket build() {
            return serviceTicket;
        }
    }
}

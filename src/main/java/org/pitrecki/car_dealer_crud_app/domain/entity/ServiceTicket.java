package org.pitrecki.car_dealer_crud_app.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "service_ticket")
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"parts", "car"})
@ToString(exclude = {"parts", "car"})
@Builder(builderMethodName = "aServiceTicket", setterPrefix = "with")
public class ServiceTicket extends BaseEntity {
    @NotNull @Column(name = "description", nullable = false) private String description;

    @ManyToOne @JoinColumn(name = "car_id", nullable = false) private Car car;

    @Builder.Default
    @NotNull @Column(name = "start_date", nullable = false) private LocalDate startDate = LocalDate.now();
    @FutureOrPresent @Column(name = "end_date") private LocalDate endDate;
    
    @ManyToMany(mappedBy = "tickets")
    private List<Part> parts;

    public ServiceTicket(@NotNull String description) {
        this.description = description;
    }
}

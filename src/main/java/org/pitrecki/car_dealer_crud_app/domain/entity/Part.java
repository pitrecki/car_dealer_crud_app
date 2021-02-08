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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "part")
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
@Data
@EqualsAndHashCode(callSuper = false, exclude = {"tickets", "car"})
@ToString(exclude = {"tickets", "car"})
@Builder(builderMethodName = "aPart", setterPrefix = "with")
public class Part extends BaseEntity {
    @NotNull @Column(name = "name", nullable = false) private String name;
    @NotNull @Column(name = "description", nullable = false) private String description;
    @NotNull @Column(name = "price") private BigDecimal price;

    @Builder.Default
    @Column(name = "available") private Boolean isAvailable = false;

    @Builder.Default
    @Positive @Column(name = "days_to_dispatch") private Integer daysToDispatch = 0;


    @ManyToOne @JoinColumn(name = "car_id") private Car car;

    @Builder.Default
    @ManyToMany(cascade = ALL)
    @JoinTable(
            name = "part_service",
            joinColumns = { @JoinColumn(name = "part_id") },
            inverseJoinColumns = { @JoinColumn(name = "service_id") }
    )
    private List<ServiceTicket> tickets = new ArrayList<>();

    public Part(@NotNull String name, @NotNull String description, @NotNull BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}

package org.pitrecki.car_dealer_crud_app.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Embeddable
@NoArgsConstructor(access = PRIVATE)
@Getter
public class Period {

    private LocalDate startDate;

    private LocalDate endDate;

    public Period(LocalDate startDate, LocalDate endDate) {
        checkIntegralityOfData(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setStartDate(LocalDate startDate) {
        checkIntegralityOfData(startDate, this.endDate);
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        checkIntegralityOfData(this.startDate, endDate);
        this.endDate = endDate;
    }

    private void checkIntegralityOfData(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be greater than end date");
        }
    }
}

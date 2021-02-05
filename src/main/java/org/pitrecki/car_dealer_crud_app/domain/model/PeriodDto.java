package org.pitrecki.car_dealer_crud_app.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PeriodDto {
    @PastOrPresent
    @DateTimeFormat(iso = DATE)
    private LocalDate startDate;

    @FutureOrPresent
    @DateTimeFormat(iso = DATE)
    private LocalDate endDate;
}

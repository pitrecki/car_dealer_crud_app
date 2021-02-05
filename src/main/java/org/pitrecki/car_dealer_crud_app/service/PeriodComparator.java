package org.pitrecki.car_dealer_crud_app.service;

import java.time.LocalDate;
import java.util.Comparator;

public class PeriodComparator implements Comparator<LocalDate> {

    @Override
    public int compare(LocalDate o1, LocalDate o2) {
        return o1.compareTo(o2);
    }
}

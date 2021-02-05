package org.pitrecki.car_dealer_crud_app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pitrecki.car_dealer_crud_app.domain.adapter.DBCarAdapter;
import org.pitrecki.car_dealer_crud_app.domain.entity.Car;
import org.pitrecki.car_dealer_crud_app.domain.entity.ServiceTicket;
import org.pitrecki.car_dealer_crud_app.domain.model.CarDto;
import org.pitrecki.car_dealer_crud_app.domain.model.PeriodDto;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_CAR;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_DATE;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_INTERGER;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_SERVICE_TICKET;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_STRING;

@ExtendWith(MockitoExtension.class)
class ServiceTicketServiceTest {

    private static final CarDto CAR = new CarDto(DUMMY_STRING, DUMMY_STRING);
    private static final PeriodDto PERIOD = givenPeriod(DUMMY_DATE, DUMMY_DATE.plusDays(1));

    @Mock private DBCarAdapter carAdapter;
    @InjectMocks private ServiceTicketService service;

    @ParameterizedTest
    @MethodSource("dateParams")
    void shouldFindServiceTicketsInWithinPeriod(LocalDate startDate, LocalDate endDate) {
        given(carAdapter.findCarByModelAndMake(anyString(), anyString())).willReturn(Optional.of(DUMMY_CAR));

        Stream<ServiceTicket> result = service.findAllServiceTicketsWithinDate(CAR, givenPeriod(startDate, endDate));

        assertThat(result).contains(DUMMY_SERVICE_TICKET);
    }

    private static Stream<Arguments> dateParams() {
        return Stream.of(
          Arguments.of(DUMMY_DATE, DUMMY_DATE.plusDays(1)),
          Arguments.of(DUMMY_DATE.plusDays(1), DUMMY_DATE.plusDays(DUMMY_INTERGER)),
          Arguments.of(DUMMY_DATE.minusDays(1), DUMMY_DATE)
        );
    }

    @Test
    void shouldReturnEmptyStreamWhenCarIsNotFound() {
        Stream<ServiceTicket> result = service.findAllServiceTicketsWithinDate(CAR, PERIOD);

        assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnEmptyStreamWhenCarDoesNotContainsAnyTickets() {
        given(carAdapter.findCarByModelAndMake(anyString(), anyString())).willReturn(Optional.of(new Car()));

        Stream<ServiceTicket> result = service.findAllServiceTicketsWithinDate(CAR, PERIOD);

        assertThat(result).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("notMatchDatesParams")
    void shouldReturnEmptyStreamWhenCarPeriodDoesNotMatchCriteria(LocalDate startDate, LocalDate endDate) {
        given(carAdapter.findCarByModelAndMake(anyString(), anyString())).willReturn(Optional.of(DUMMY_CAR));

        Stream<ServiceTicket> result = service.findAllServiceTicketsWithinDate(CAR, givenPeriod(startDate, endDate));

        assertThat(result).isEmpty();
    }

    private static Stream<Arguments> notMatchDatesParams() {
        return Stream.of(
                Arguments.of(DUMMY_DATE.minusDays(1000), DUMMY_DATE.minusDays(999)),
                Arguments.of(DUMMY_DATE.plusDays(999), DUMMY_DATE.plusDays(1000))
        );
    }

    private static PeriodDto givenPeriod(LocalDate startDate, LocalDate endDate) {
        return new PeriodDto(startDate, endDate);
    }
}

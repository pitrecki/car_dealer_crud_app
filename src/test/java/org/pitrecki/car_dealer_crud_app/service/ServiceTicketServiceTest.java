package org.pitrecki.car_dealer_crud_app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pitrecki.car_dealer_crud_app.domain.adapter.DBCarAdapter;
import org.pitrecki.car_dealer_crud_app.domain.adapter.DBPartAdapter;
import org.pitrecki.car_dealer_crud_app.domain.adapter.DBServiceTickerAdapter;
import org.pitrecki.car_dealer_crud_app.domain.entity.Car;
import org.pitrecki.car_dealer_crud_app.domain.entity.ServiceTicket;
import org.pitrecki.car_dealer_crud_app.domain.model.CarDto;
import org.pitrecki.car_dealer_crud_app.domain.model.ServiceTicketRequest;
import org.pitrecki.car_dealer_crud_app.domain.model.TimespanRequest;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.pitrecki.car_dealer_crud_app.utils.TestDummies.DUMMY_CAR;
import static org.pitrecki.car_dealer_crud_app.utils.TestDummies.DUMMY_DATE;
import static org.pitrecki.car_dealer_crud_app.utils.TestDummies.DUMMY_ID;
import static org.pitrecki.car_dealer_crud_app.utils.TestDummies.DUMMY_INTERGER;
import static org.pitrecki.car_dealer_crud_app.utils.TestDummies.DUMMY_PART;
import static org.pitrecki.car_dealer_crud_app.utils.TestDummies.DUMMY_SERVICE_TICKET;
import static org.pitrecki.car_dealer_crud_app.utils.TestDummies.DUMMY_STRING;

@ExtendWith(MockitoExtension.class)
class ServiceTicketServiceTest {

    private static final CarDto CAR = new CarDto(DUMMY_STRING, DUMMY_STRING);
    private static final TimespanRequest PERIOD = givenPeriod(DUMMY_DATE, DUMMY_DATE.plusDays(1));
    private static final ServiceTicketRequest TICKET_REQUEST = new ServiceTicketRequest(DUMMY_ID, DUMMY_STRING);

    @Mock private DBCarAdapter carAdapter;
    @Mock private DBPartAdapter partAdapter;
    @Mock private DBServiceTickerAdapter serviceTickerAdapter;
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

    @Test
    void shouldAddTicketWhenPartExistInDb() {
        ArgumentCaptor<ServiceTicket> captor = ArgumentCaptor.forClass(ServiceTicket.class);

        given(partAdapter.findPartById(DUMMY_ID)).willReturn(DUMMY_PART);

        service.addTicket(TICKET_REQUEST);

        then(serviceTickerAdapter).should().addTicket(captor.capture());
        assertThat(captor.getValue())
                .extracting(ServiceTicket::getDescription)
                .isEqualTo(DUMMY_STRING);
    }

    @Test
    void shouldThrowsExceptionWhenPartDoesNotExists() {
        willThrow(EntityNotFoundException.class).given(partAdapter).findPartById(DUMMY_ID);

        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> service.addTicket(TICKET_REQUEST));
    }

    private static TimespanRequest givenPeriod(LocalDate startDate, LocalDate endDate) {
        return new TimespanRequest(startDate, endDate);
    }
}

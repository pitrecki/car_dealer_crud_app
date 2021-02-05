package org.pitrecki.car_dealer_crud_app.domain.adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pitrecki.car_dealer_crud_app.domain.entity.ServiceTicket;
import org.pitrecki.car_dealer_crud_app.domain.repository.ServiceTicketRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.pitrecki.car_dealer_crud_app.utils.TestDummies.DUMMY_SERVICE_TICKET;

@ExtendWith(MockitoExtension.class)
class DBServiceTickerAdapterTest {

    private final ArgumentCaptor<ServiceTicket> captor = ArgumentCaptor.forClass(ServiceTicket.class);

    @Mock private ServiceTicketRepository repository;
    @InjectMocks private DBServiceTickerAdapter adapter;

    @Test
    void shouldAddNewTicketIntoDb() {
        adapter.addTicket(DUMMY_SERVICE_TICKET);

        then(repository).should().save(captor.capture());
        assertThat(captor.getValue())
                .isEqualTo(DUMMY_SERVICE_TICKET);
    }
}
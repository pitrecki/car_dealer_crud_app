package org.pitrecki.car_dealer_crud_app.api;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.pitrecki.car_dealer_crud_app.AbstractIntegrationTest;
import org.pitrecki.car_dealer_crud_app.domain.entity.ServiceTicket;
import org.pitrecki.car_dealer_crud_app.domain.repository.ServiceTicketRepository;
import org.pitrecki.car_dealer_crud_app.helpers.ParamEntries;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.pitrecki.car_dealer_crud_app.helpers.ParamEntries.ParamEntry.entry;
import static org.pitrecki.car_dealer_crud_app.helpers.ParamEntries.aParamEntries;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ServiceTicketControllerIntegrationTest extends AbstractIntegrationTest {

    private static final String REQUEST_URL = "/api/service";
    private static final String SOME_MODEL = "Prelude III";
    private static final String SOME_MAKE = "Honda";
    private static final String SOME_START_DATE = "2003-09-09";
    private static final String SOME_DESCRIPTION = "2f9x1UzvvC";
    private static final String SOME_TICKET_START_DATE = "2003-04-25";

    @Autowired private ServiceTicketRepository repository;

    @Test
    @Order(1)
    void shouldReturnStatusOkAndServiceTicketsForCar() throws Exception {
        ParamEntries params = aParamEntries()
                .add(entry("model", SOME_MODEL))
                .add(entry("make", SOME_MAKE))
                .add(entry("startDate", SOME_START_DATE))
                .add(entry("endDate", LocalDate.now().toString()));

        doGet(REQUEST_URL, params)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..description").value(SOME_DESCRIPTION))
                .andExpect(jsonPath("$..period.startDate").value(SOME_TICKET_START_DATE));
    }


    @Test
    void shouldReturnStatusOkAndAddTicketToDb() throws Exception {
        Iterable<ServiceTicket> before = repository.findAll();
        assertThat(before).hasSize(1);

        doPost(REQUEST_URL, "{\"id\": 1, \"description\": \"someDesc\"}")
                .andExpect(status().isOk());

        Iterable<ServiceTicket> actual = repository.findAll();
        assertThat(actual).hasSize(2);
    }

    @Test
    void shouldReturnBadRequestWhenUnableToAddServiceTicket() throws Exception {
        doPost(REQUEST_URL, "{\"id\": 2, \"description\": \"someDesc\"}")
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Unable to find part by given id: 2"));

    }
}

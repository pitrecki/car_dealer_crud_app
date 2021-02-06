package org.pitrecki.car_dealer_crud_app.rest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.pitrecki.car_dealer_crud_app.service.ServiceTicketService;
import org.pitrecki.car_dealer_crud_app.utils.ObjectMapper;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static java.util.List.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.pitrecki.car_dealer_crud_app.utils.TestDummies.DUMMY_DATE;
import static org.pitrecki.car_dealer_crud_app.utils.TestDummies.DUMMY_SERVICE_TICKET;
import static org.pitrecki.car_dealer_crud_app.utils.TestDummies.DUMMY_STRING;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class ServiceTicketControllerTest {

    @Mock
    private ServiceTicketService service;

    private ServiceTicketController controller;
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        controller = new ServiceTicketController(service, new ObjectMapper(mapper));
        mvc = standaloneSetup(controller)
                .build();
    }

    @Test
    void shouldReturnStatusOkAndServiceTicketsForCar() throws Exception {
        given(service.findAllServiceTicketsWithinDate(any(), any()))
                .willReturn(Stream.of(DUMMY_SERVICE_TICKET, DUMMY_SERVICE_TICKET));

        mvc.perform(request(GET, "/api/service")
                .param("model", DUMMY_STRING)
                .param("make", DUMMY_STRING)
                .param("startDate", DUMMY_DATE.toString())
                .param("endDate", DUMMY_DATE.plusDays(1).toString()))
                .andExpect(
                        matchAll(status().isOk(),
                                jsonPath("$..description", of(DUMMY_STRING, DUMMY_STRING)).hasJsonPath(),
                                jsonPath("$..period.startDate", of(DUMMY_DATE, DUMMY_DATE)).hasJsonPath()
                        ));
    }

    @Test
    void shouldReturnStatusOkAndAddTicketToDb() throws Exception {
        mvc.perform(request(POST, "/api/service")
                .contentType(APPLICATION_JSON)
                .content("{\"id\": 1, \"description\": \"someDesc\"}"))
                .andExpect(status().isOk());
    }
}

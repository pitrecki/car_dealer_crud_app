package org.pitrecki.car_dealer_crud_app.rest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.pitrecki.car_dealer_crud_app.service.CarService;
import org.pitrecki.car_dealer_crud_app.utils.ObjectMapper;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static java.util.List.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_BOOLEAN;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_INTERGER;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_PART;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_PRICE;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_STRING;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock private CarService service;

    private CarController controller;
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        controller = new CarController(service, new ObjectMapper(mapper));
        mvc = standaloneSetup(controller)
                .build();
    }

    @Test
    void shouldReturnStatusOkAndFoundPartsByName() throws Exception {
        given(service.findPartsByName(anyString(), any())).willReturn(Stream.of(DUMMY_PART, DUMMY_PART));

        performRequest("partName");
    }

    @Test
    void shouldReturnStatusOkAndFoundPartsByDescription() throws Exception {
        given(service.findPartsByDescription(anyString(), any())).willReturn(Stream.of(DUMMY_PART, DUMMY_PART));

        performRequest("partDesc");
    }

    private void performRequest(String param) throws Exception {
        mvc.perform(request(GET, "/api/car")
                .param(param, DUMMY_STRING)
                .param("model", DUMMY_STRING)
                .param("make", DUMMY_STRING))
                .andExpect(
                        matchAll(status().isOk(),
                                jsonPath("$..description", of(DUMMY_STRING, DUMMY_STRING)).hasJsonPath(),
                                jsonPath("$..name", of(DUMMY_STRING, DUMMY_STRING)).hasJsonPath(),
                                jsonPath("$..daysToDispatch", of(DUMMY_INTERGER, DUMMY_INTERGER)).hasJsonPath(),
                                jsonPath("$..isAvailable", of(DUMMY_BOOLEAN, DUMMY_BOOLEAN)).hasJsonPath(),
                                jsonPath("$..price", of(DUMMY_PRICE, DUMMY_PRICE)).hasJsonPath()
                        ));
    }
}

package org.pitrecki.car_dealer_crud_app.rest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.pitrecki.car_dealer_crud_app.rest.error_handling.EntityExceptionHandler;
import org.pitrecki.car_dealer_crud_app.service.PartService;
import org.pitrecki.car_dealer_crud_app.utils.ObjectMapper;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;

import static java.util.List.of;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_BOOLEAN;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_INTERGER;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_PART;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class PartControllerTest {

    @Mock private PartService service;
    @InjectMocks private PartController controller;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        ModelMapper mapper = new ModelMapper();

        controller = new PartController(service, new ObjectMapper(mapper));
        mvc = standaloneSetup(controller)
                .setControllerAdvice(new EntityExceptionHandler())
                .build();
    }

    @Test
    void shouldReturnStatusOkWhenSuccessfullyUpdateDescription() throws Exception {
        mvc.perform(request(PUT, "/api/part/1")
                .param("desc", "some_desc"))
                .andExpect(matchAll(status().isOk()));

        then(service).should().updatePartDescription(anyLong(), anyString());
    }

    @Test
    void shouldReturnStatusBadRequestWhenTryingToUpdateDescriptionForNotExistingPart() throws Exception {
        willThrow(EntityNotFoundException.class).given(service).updatePartDescription(anyLong(), anyString());

        mvc.perform(request(PUT, "/api/part/1")
                .param("desc", "some_desc"))
                .andExpect(matchAll(status().isBadRequest()));
    }

    @Test
    void shouldReturnsStatusOkAndPartWhenSucessfulyFoundById() throws Exception {
        given(service.findPartAvailability(anyLong())).willReturn(DUMMY_PART);

        mvc.perform(request(GET, "/api/part/1"))
                .andExpect(matchAll(status().isOk(),
                        jsonPath("$..isAvailable", of(DUMMY_BOOLEAN, DUMMY_BOOLEAN)).hasJsonPath(),
                        jsonPath("$..daysToDispatch", of(DUMMY_INTERGER, DUMMY_INTERGER)).hasJsonPath())
                );
    }

    @Test
    void shouldReturnsBadRequestWhenPartIsNotFound() throws Exception {
        willThrow(EntityNotFoundException.class).given(service).findPartAvailability(anyLong());


        mvc.perform(request(GET, "/api/part/1"))
                .andExpect(matchAll(status().isBadRequest()));
    }
}
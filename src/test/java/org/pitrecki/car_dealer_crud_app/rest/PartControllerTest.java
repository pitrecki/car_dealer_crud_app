package org.pitrecki.car_dealer_crud_app.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pitrecki.car_dealer_crud_app.rest.error_handling.EntityExceptionHandler;
import org.pitrecki.car_dealer_crud_app.service.PartService;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityNotFoundException;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class PartControllerTest {

    @Mock private PartService service;
    @InjectMocks private PartController controller;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
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
}
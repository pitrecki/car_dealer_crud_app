package org.pitrecki.car_dealer_crud_app.api;

import org.junit.jupiter.api.Test;
import org.pitrecki.car_dealer_crud_app.AbstractIntegrationTest;
import org.pitrecki.car_dealer_crud_app.domain.entity.Part;
import org.pitrecki.car_dealer_crud_app.domain.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PartControllerIntegrationTest extends AbstractIntegrationTest {

    private static final long ID = 1L;
    private static final String NEW_DESCRIPTION = "some_desc";
    private static final String ERROR_MESSAGE = "Unable to update description";

    @Autowired private MockMvc mvc;
    @Autowired private PartRepository repository;

    @Test
    void shouldSuccessfullyUpdatePartDescription() throws Exception {
        String olDescription = repository.findById(ID).get().getDescription();

        mvc.perform(request(PUT, "/api/part/1")
                .param("desc", NEW_DESCRIPTION))
                .andExpect(matchAll(status().isOk()));

        Optional<Part> result = repository.findById(ID);

        assertThat(result)
                .map(Part::getDescription)
                .hasValue(NEW_DESCRIPTION)
                .isNotEqualTo(olDescription);
    }

    @Test
    void shouldHandleExeceptionWhenUnableToUpdateDescription() throws Exception {
        mvc.perform(request(PUT, "/api/part/2")
                .param("desc", NEW_DESCRIPTION))
                .andExpect(matchAll(status().isBadRequest(),
                        content().string(ERROR_MESSAGE)));
    }
}

package org.pitrecki.car_dealer_crud_app.api;

import org.junit.jupiter.api.Test;
import org.pitrecki.car_dealer_crud_app.AbstractIntegrationTest;
import org.pitrecki.car_dealer_crud_app.domain.entity.Part;
import org.pitrecki.car_dealer_crud_app.domain.repository.PartRepository;
import org.pitrecki.car_dealer_crud_app.helpers.ParamEntries;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.pitrecki.car_dealer_crud_app.helpers.ParamEntries.ParamEntry.entry;
import static org.pitrecki.car_dealer_crud_app.helpers.ParamEntries.aParamEntries;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PartControllerIntegrationTest extends AbstractIntegrationTest {

    private static final String REQUEST_URL = "/api/part/%s";
    private static final long ID = 1L;
    private static final String NEW_DESCRIPTION = "some_desc";
    private static final ParamEntries PARAMS = aParamEntries().add(entry("desc", NEW_DESCRIPTION));
    private static final String ERROR_MESSAGE = "Unable to update description";
    private static final boolean IS_AVAILABLE = true;
    private static final int DAYS = 26;

    @Autowired
    private PartRepository repository;

    @Test
    void shouldSuccessfullyUpdatePartDescription() throws Exception {
        String oldDescription = repository.findById(ID).get().getDescription();

        doPut(format(REQUEST_URL, "1"), PARAMS)
                .andExpect(status().isOk());

        Optional<Part> result = repository.findById(ID);

        assertThat(result)
                .map(Part::getDescription)
                .hasValue(NEW_DESCRIPTION)
                .isNotEqualTo(oldDescription);
    }

    @Test
    void shouldHandleExceptionWhenUnableToUpdateDescription() throws Exception {
        doPut(format(REQUEST_URL, "2"), PARAMS)
                .andExpect(status().isBadRequest())
                .andExpect(content().string(ERROR_MESSAGE));
    }

    @Test
    void shouldReturnsStatusOkAndPartWhenSuccessfullyFoundById() throws Exception {
        doGet(format(REQUEST_URL, "1"), PARAMS)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..isAvailable").value(IS_AVAILABLE))
                .andExpect(jsonPath("$..daysToDispatch").value(DAYS));
    }

}

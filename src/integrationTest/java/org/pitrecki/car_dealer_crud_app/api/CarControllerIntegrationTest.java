package org.pitrecki.car_dealer_crud_app.api;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.pitrecki.car_dealer_crud_app.AbstractIntegrationTest;
import org.pitrecki.car_dealer_crud_app.helpers.ParamEntries;
import org.springframework.test.web.servlet.ResultActions;

import java.util.stream.Stream;

import static org.pitrecki.car_dealer_crud_app.helpers.ParamEntries.ParamEntry.entry;
import static org.pitrecki.car_dealer_crud_app.helpers.ParamEntries.aParamEntries;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CarControllerIntegrationTest extends AbstractIntegrationTest {

    private static final String REQUEST_URL = "/api/car";
    private static final String EMPTY = "";
    private static final String EMPTY_RESPONE = "[]";
    private static final String SOME_DESCRIPTION = "cKlpbT5CWu";
    private static final String SOME_NAME = "RDO9HVTiMS";
    private static final int DAYS_TO_DISPATCH = 26;
    private static final boolean IS_AVAILAIBLE = true;
    private static final double SOME_PRICE = 364.00;

    @ParameterizedTest
    @MethodSource("partNameParams")
    void shouldReturnStatusOkAndFoundPartsByName(String name) throws Exception {
        ResultActions result = doGet(REQUEST_URL, params("partName", name));

        assertThatResponse(result);
    }

    private static Stream<Arguments> partNameParams() {
        return Stream.of(
                Arguments.of("RDO9HVTiMS"),
                Arguments.of("9"),
                Arguments.of("HVT"),
                Arguments.of("TiMS"),
                Arguments.of("RDO9H")
        );
    }

    @ParameterizedTest
    @MethodSource("partDescriptionParams")
    void shouldReturnStatusOkAndFoundPartsByDescription(String description) throws Exception {
        ResultActions result = doGet(REQUEST_URL, params("partDesc", description));

        assertThatResponse(result);
    }

    private static Stream<Arguments> partDescriptionParams() {
        return Stream.of(
                Arguments.of("cKlpbT5CWu"),
                Arguments.of("W"),
                Arguments.of("cKlp"),
                Arguments.of("T5CWu")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"partDesc", "partName"})
    void shouldReturnStatusOkAndEmptyResponse(String key) throws Exception {
        ResultActions result = doGet(REQUEST_URL, params(key, EMPTY)).andDo(print());

        assertThatEmptyResponse(result);
    }

    private static void assertThatResponse(ResultActions actions) throws Exception {
            actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..description").value(SOME_DESCRIPTION))
                .andExpect(jsonPath("$..name").value(SOME_NAME))
                .andExpect(jsonPath("$..daysToDispatch").value(DAYS_TO_DISPATCH))
                .andExpect(jsonPath("$..isAvailable").value(IS_AVAILAIBLE))
                .andExpect(jsonPath("$..price").value(SOME_PRICE));
    }

    private static void assertThatEmptyResponse(ResultActions actions) throws Exception {
        actions
            .andExpect(status().isOk())
            .andExpect(content().json(EMPTY_RESPONE));
    }

    private static ParamEntries params(String key, String values) {
        return aParamEntries()
                .add(entry("model", "Prelude III"))
                .add(entry("make", "Honda"))
                .add(entry(key, values));
    }
}

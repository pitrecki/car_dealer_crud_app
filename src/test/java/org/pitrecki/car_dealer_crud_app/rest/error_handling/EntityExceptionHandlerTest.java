package org.pitrecki.car_dealer_crud_app.rest.error_handling;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ExtendWith(MockitoExtension.class)
class EntityExceptionHandlerTest {

    private static final String SOME_MESSAGE = "someMessage";

    @Mock private WebRequest webRequest;

    @Test
    void shouldHandleExceptionAndReturnExpectedMessageAndStatusCode() {
        EntityExceptionHandler handler = new EntityExceptionHandler();

        ResponseEntity<Object> result = handler.handle(new Exception(SOME_MESSAGE), webRequest);

        assertThat(result)
                .extracting(ResponseEntity::getStatusCode, HttpEntity::getBody)
                .containsExactly(BAD_REQUEST, SOME_MESSAGE);
    }
}
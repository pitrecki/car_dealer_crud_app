package org.pitrecki.car_dealer_crud_app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pitrecki.car_dealer_crud_app.domain.adapter.DBPartAdapter;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;

@ExtendWith(MockitoExtension.class)
class PartServiceTest {

    private static final String SOME_DESCRIPTION = "someDesc";
    private static final long SOME_ID = 1L;
    @Mock private DBPartAdapter adapter;
    @InjectMocks private PartService service;

    @Test
    void shouldUpdatePartDescription() {
        service.updatePartDescription(SOME_ID, SOME_DESCRIPTION);

        then(adapter).should().updateDescriptionById(SOME_ID, SOME_DESCRIPTION);
    }

    @Test
    void shouldThrowsExceptionWhenUnableToUpdateDescription() {
        willThrow(EntityNotFoundException.class).given(adapter).updateDescriptionById(SOME_ID, SOME_DESCRIPTION);

        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> service.updatePartDescription(SOME_ID, SOME_DESCRIPTION));
    }
}
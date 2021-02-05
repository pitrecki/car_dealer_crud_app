package org.pitrecki.car_dealer_crud_app.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pitrecki.car_dealer_crud_app.domain.adapter.DBPartAdapter;
import org.pitrecki.car_dealer_crud_app.domain.entity.Part;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_ID;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_PART;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_STRING;

@ExtendWith(MockitoExtension.class)
class PartServiceTest {

    @Mock private DBPartAdapter adapter;
    @InjectMocks private PartService service;

    @Test
    void shouldUpdatePartDescription() {
        service.updatePartDescription(DUMMY_ID, DUMMY_STRING);

        then(adapter).should().updateDescriptionById(DUMMY_ID, DUMMY_STRING);
    }

    @Test
    void shouldThrowsExceptionWhenUnableToUpdateDescription() {
        willThrow(EntityNotFoundException.class).given(adapter).updateDescriptionById(DUMMY_ID, DUMMY_STRING);

        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> service.updatePartDescription(DUMMY_ID, DUMMY_STRING));
    }

    @Test
    void shouldFindPartById() {
        given(adapter.findPartById(DUMMY_ID)).willReturn(DUMMY_PART);

        Part result = service.findPartAvailability(DUMMY_ID);

        assertThat(result).isEqualTo(DUMMY_PART);
    }

    @Test
    void shouldThrowsExceptionWhenPartNotFound() {
        willThrow(EntityNotFoundException.class).given(adapter).findPartById(DUMMY_ID);

        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> service.findPartAvailability(DUMMY_ID));
    }
}
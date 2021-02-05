package org.pitrecki.car_dealer_crud_app.domain.adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.pitrecki.car_dealer_crud_app.domain.entity.Part;
import org.pitrecki.car_dealer_crud_app.domain.repository.PartRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_ID;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_PART;
import static org.pitrecki.car_dealer_crud_app.utils.TestDumies.DUMMY_STRING;

@ExtendWith(MockitoExtension.class)
class DBPartAdapterTest {

    private final ArgumentCaptor<Part> captor = ArgumentCaptor.forClass(Part.class);

    @Mock private PartRepository repository;
    @InjectMocks private DBPartAdapter adapter;

    @Test
    void shouldUpdatePartDescriptionByGivenId() {
        given(repository.findById(DUMMY_ID)).willReturn(Optional.of(DUMMY_PART));

        adapter.updateDescriptionById(DUMMY_ID, DUMMY_STRING);

        then(repository).should().save(captor.capture());

        assertThat(captor.getAllValues())
                .extracting(Part::getDescription)
                .containsExactly(DUMMY_STRING);
    }

    @Test
    void shouldThrowsExceptionWhenUnableToUpdateDescription() {
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> adapter.updateDescriptionById(DUMMY_ID, DUMMY_STRING))
                .withMessage("Unable to update description");
    }

    @Test
    void shouldFindPartById() {
        given(repository.findById(DUMMY_ID)).willReturn(Optional.of(DUMMY_PART));

        Part result = adapter.findPartById(DUMMY_ID);

        assertThat(result).isEqualTo(DUMMY_PART);
    }

    @Test
    void shouldThrowsExceptionWhenNotFoundPartById() {
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> adapter.findPartById(DUMMY_ID))
                .withMessageContaining("Unable to find part by given id");
    }
}
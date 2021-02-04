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

@ExtendWith(MockitoExtension.class)
class DBPartAdapterTest {

    private static final long SOME_ID = 1L;
    private static final String SOME_DESCRIPTION = "someDesc";
    private static final Part SOME_PART = new Part();
    private static final String EXCEPTION_MESSAGE = "Unable to update description";

    private final ArgumentCaptor<Part> captor = ArgumentCaptor.forClass(Part.class);

    @Mock private PartRepository repository;
    @InjectMocks private DBPartAdapter adapter;

    @Test
    void shouldUpdatePartDescriptionByGivenId() {
        given(repository.findById(SOME_ID)).willReturn(Optional.of(SOME_PART));

        adapter.updateDescriptionById(SOME_ID, SOME_DESCRIPTION);

        then(repository).should().save(captor.capture());

        assertThat(captor.getAllValues())
                .extracting(Part::getDescription)
                .containsExactly(SOME_DESCRIPTION);
    }

    @Test
    void shouldThrowsExceptionWhenUnableToUpdateDescription() {
        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> adapter.updateDescriptionById(SOME_ID, SOME_DESCRIPTION))
                .withMessage(EXCEPTION_MESSAGE);
    }
}
package org.pitrecki.car_dealer_crud_app.domain.adapter;

import lombok.RequiredArgsConstructor;
import org.pitrecki.car_dealer_crud_app.domain.entity.Part;
import org.pitrecki.car_dealer_crud_app.domain.repository.PartRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class DBPartAdapter {

    private final PartRepository repository;

    public void updateDescriptionById(Long id, String description) {
        repository.findById(id)
        .ifPresentOrElse(p -> {
            p.setDescription(description);
            repository.save(p);
        }, () -> {throw new EntityNotFoundException("Unable to update description");});
    }

    public Part findPartById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("Unable to find part by given id: %d", id)));
    }
}

package org.pitrecki.car_dealer_crud_app.service;

import lombok.RequiredArgsConstructor;
import org.pitrecki.car_dealer_crud_app.aspect.Logging;
import org.pitrecki.car_dealer_crud_app.domain.adapter.DBPartAdapter;
import org.pitrecki.car_dealer_crud_app.domain.entity.Part;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PartService {

    private final DBPartAdapter adapter;

    @Logging
    public void updatePartDescription(Long id, String description) {
        adapter.updateDescriptionById(id, description);
    }

    @Logging
    public Part findPartAvailability(Long id) {
        return adapter.findPartById(id);
    }
}

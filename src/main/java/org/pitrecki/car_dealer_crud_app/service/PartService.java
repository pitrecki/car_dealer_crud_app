package org.pitrecki.car_dealer_crud_app.service;

import lombok.RequiredArgsConstructor;
import org.pitrecki.car_dealer_crud_app.domain.adapter.DBPartAdapter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartService {

    private final DBPartAdapter adapter;

    public void updatePartDescription(Long id, String description) {
        adapter.updateDescriptionById(id, description);
    }
}

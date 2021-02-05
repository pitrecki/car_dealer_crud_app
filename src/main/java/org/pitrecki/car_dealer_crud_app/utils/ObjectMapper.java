package org.pitrecki.car_dealer_crud_app.utils;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.pitrecki.car_dealer_crud_app.domain.entity.BaseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ObjectMapper {
    private final ModelMapper mapper;

    public <E> E mapToDto(BaseEntity entity, Class<E> clazz) {
        return mapper.map(entity, clazz);
    }
}

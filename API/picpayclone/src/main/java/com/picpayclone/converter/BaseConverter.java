package com.picpayclone.converter;

import java.util.ArrayList;
import java.util.List;

// Generic classes <E = Entity, D = DTO>
public abstract class BaseConverter<E, D> {

    public abstract D convertEntityToDTO(E entity);

    public abstract E convertDTOToEntity(D dto);

    public List<D> convertEntitiesToDTOs(List<E> entities) {
        List<D> dtos = new ArrayList<>();
        entities.stream().forEach(entity -> dtos.add(convertEntityToDTO(entity)));
        return dtos;
    }

    public List<E> convertDTOsToEntities(List<D> dtos) {
        List<E> entities = new ArrayList<>();
        dtos.stream().forEach(dto -> entities.add(convertDTOToEntity(dto)));
        return entities;
    }
}

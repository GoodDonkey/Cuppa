package com.cuppa.cuppa.main.domain;

@FunctionalInterface
public interface DTOMapper<T extends TransferObject, S extends MappableEntity> {
    
    T translate(S entity);
}

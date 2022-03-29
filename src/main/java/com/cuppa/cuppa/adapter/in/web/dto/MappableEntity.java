package com.cuppa.cuppa.adapter.in.web.dto;

/**
 * DTO 로 변환할 수 있는 객체임을 의미한다.*/
public interface MappableEntity<T extends TransferObject> {
    
    /**
     * Entity의 속성만 가지고 DTO로 변환한다.
     * 외부 의존관계를 가지는 DTO 로 변환하기 위해서는 DTOMapper를 구현할 것. */
    T toSimpleDTO();
}

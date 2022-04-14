package com.cuppa.cuppa.adapter.in.web.dto;


/**
 * DTO 로 변환하기 위해 외부 의존관계를 가지는 경우, 이 인터페이스를 구현하여 사용한다.
 * 또는 Repository의 쿼리를 포함하는 과정을 거쳐야 할 경우 이 인터페이스를 구현한다.*/
public interface DTOMapper<T extends TransferObject, S> {
    
    T map(S obj);
}

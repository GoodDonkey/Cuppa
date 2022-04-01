package com.cuppa.cuppa.application.port.in;

import com.cuppa.cuppa.domain.LoginEntity;

public interface LoginUseCase<T extends LoginEntity> {
    
    T login(String loginId, String password);
}
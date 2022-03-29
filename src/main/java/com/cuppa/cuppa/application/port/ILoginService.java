package com.cuppa.cuppa.application.port;

import com.cuppa.cuppa.domain.LoginEntity;

public interface ILoginService<T extends LoginEntity> {
    
    T login(String loginId, String password);
}

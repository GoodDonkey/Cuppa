package com.cuppa.cuppa.login.service;

import com.cuppa.cuppa.login.domain.LoginEntity;

public interface ILoginService<T extends LoginEntity> {
    
    T login(String loginId, String password);
}

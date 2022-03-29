package com.cuppa.cuppa.adapter.in.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginForm {
    
    @NotNull
    private String loginId;
    
    @NotNull
    private String password;
}

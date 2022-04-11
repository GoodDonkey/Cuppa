package com.cuppa.cuppa.adapter.in.web.dto;

import com.cuppa.cuppa.domain.Role;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NotNull
@AllArgsConstructor
public class MemberSignupRequestDTO {
    
    private String loginId;
    private String password;
    private String username;
    private Role role;
}

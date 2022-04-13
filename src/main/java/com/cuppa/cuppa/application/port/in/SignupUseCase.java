package com.cuppa.cuppa.application.port.in;

import com.cuppa.cuppa.adapter.in.web.dto.MemberSignupRequestDTO;
import com.cuppa.cuppa.domain.Member;

public interface SignupUseCase {

    Member signUp(MemberSignupRequestDTO memberSignupRequestDTO);
}

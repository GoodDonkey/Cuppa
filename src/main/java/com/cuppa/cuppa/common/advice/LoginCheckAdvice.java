package com.cuppa.cuppa.common.advice;

import com.cuppa.cuppa.adapter.in.web.MemberController;
import com.cuppa.cuppa.common.exceptions.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = MemberController.class)
public class LoginCheckAdvice {
    
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ErrorResult unauthorizedExHandler(UnauthorizedException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("Unauthorized", e.getMessage());
    }
}

package com.cuppa.cuppa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "error.unauthorized")
public class UnauthorizedException extends RuntimeException {
}

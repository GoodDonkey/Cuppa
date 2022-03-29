package com.cuppa.cuppa.common.advice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResult {
    
    private String code;
    private String message;
}

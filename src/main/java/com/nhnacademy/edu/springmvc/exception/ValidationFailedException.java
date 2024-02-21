package com.nhnacademy.edu.springmvc.exception;


import org.springframework.validation.BindingResult;

import javax.validation.ValidationException;
import java.util.stream.Collectors;

public class ValidationFailedException extends ValidationException {
    public ValidationFailedException(BindingResult bindingResult) {
        super(bindingResult.getAllErrors()
                .stream()
                .map(error -> new StringBuilder().append("ObjectName=").append(error.getObjectName())
                        .append(",Message=").append(error.getDefaultMessage())
                        .append(",code=").append(error.getCode()))
                .collect(Collectors.joining(" | ")));
    }
}

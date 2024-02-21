package com.nhnacademy.edu.springmvc.controller;

import com.nhnacademy.edu.springmvc.exception.ValidationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class WebControllerAdvice {

    @ExceptionHandler({ValidationFailedException.class})
    public String handleException(Exception ex, Model model) {
        log.error("exception from web controller advice");

        model.addAttribute("exception", ex);
        return "thymeleaf/error";
    }
}

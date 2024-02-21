package com.nhnacademy.edu.springmvc.domain;

import lombok.Value;

import javax.validation.constraints.*;

@Value
public class StudentRegisterDto {

    @NotBlank
    String name;

    @Email
    String email;

    @Min(0) @Max(100)
    int score;

    @NotBlank
    @Size(max = 200)
    String comment;
}

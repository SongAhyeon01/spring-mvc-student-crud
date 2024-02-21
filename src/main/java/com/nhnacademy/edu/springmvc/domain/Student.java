package com.nhnacademy.edu.springmvc.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class Student {

    private long id;

    @NotBlank
    private String name;

    @Email
    private String email;

    @Min(0) @Max(100)
    private int score;

    @NotBlank
    @Size(max = 200)
    private String comment;

    public Student(){}

    private Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static Student create(String name, String email) {
        return new Student(name, email);
    }
}

package com.nhnacademy.edu.springmvc.controller;

import com.nhnacademy.edu.springmvc.domain.Student;
import com.nhnacademy.edu.springmvc.exception.StudentNotFoundException;
import com.nhnacademy.edu.springmvc.exception.ValidationFailedException;
import com.nhnacademy.edu.springmvc.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping("/student/register")
public class StudentRegisterController {
    private final StudentRepository studentRepository;

    public StudentRegisterController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @ModelAttribute("welcome")
    public String addAttribute() {
        return "welcome";
    }

    @GetMapping
    public String studentRegisterForm() {
        return "thymeleaf/studentRegister";
    }

    @PostMapping
    public ModelAndView registerStudent(@Valid @ModelAttribute Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        ModelAndView mv = new ModelAndView();
        Student registerStudent = studentRepository.register(student.getName(), student.getEmail(), student.getScore(), student.getComment());

        mv.addObject("student", registerStudent);
        mv.setViewName("thymeleaf/studentView");

        return mv;
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(StudentNotFoundException ex, Model model) {
        model.addAttribute("exception", ex);
        return "thymeleaf/error";
    }
}

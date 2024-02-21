package com.nhnacademy.edu.springmvc.controller;

import com.nhnacademy.edu.springmvc.domain.Student;
import com.nhnacademy.edu.springmvc.domain.StudentRegisterDto;
import com.nhnacademy.edu.springmvc.exception.StudentNotFoundException;
import com.nhnacademy.edu.springmvc.exception.ValidationFailedException;
import com.nhnacademy.edu.springmvc.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/{studentId}")
    public String viewStudent(@PathVariable("studentId") long studentId, Model model) {
        Student student = studentRepository.getStudent(studentId);
        model.addAttribute("student", student);
        return "thymeleaf/studentView";
    }

    @GetMapping(value = "/{studentId}", params = "hideScore=yes")
    public ModelAndView viewStudentHideScore(@PathVariable("studentId") long studentId, @RequestParam(name = "hideScore") String hideScore) {
        ModelAndView mv= new ModelAndView();
        Student student = studentRepository.getStudent(studentId);

        mv.addObject("student", student);
        mv.addObject("hideScore", hideScore);
        mv.setViewName("thymeleaf/studentView");

        return mv;
    }

    @GetMapping("/{studentId}/modify")
    public String studentModifyForm(@PathVariable("studentId") long studentId, ModelMap modelMap) {
        if (!studentRepository.exists(studentId)) {
            throw new StudentNotFoundException();
        }
        Student student = studentRepository.getStudent(studentId);
        modelMap.addAttribute("student", student);
        return "thymeleaf/studentModify";
    }

    @PostMapping("/{studentId}/modify")
    public ModelAndView modifyStudent(@PathVariable("studentId") long studentId, @Valid @ModelAttribute StudentRegisterDto modifyRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        ModelAndView mv = new ModelAndView();

        log.debug("modify id : {}", studentId);
        try {
            Student student = studentRepository.getStudent(studentId);
            student.setName(modifyRequest.getName());
            student.setEmail(modifyRequest.getEmail());
            student.setScore(modifyRequest.getScore());
            student.setComment(modifyRequest.getComment());

            studentRepository.modify(student);
            mv.addObject("student", student);
            mv.setViewName("thymeleaf/studentView");

            return mv;
        } catch (StudentNotFoundException e) {
            throw new StudentNotFoundException();
        }
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(StudentNotFoundException ex, Model model) {
        model.addAttribute("exception", ex);
        return "thymeleaf/error";
    }

}

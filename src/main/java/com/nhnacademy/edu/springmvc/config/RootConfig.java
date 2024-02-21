package com.nhnacademy.edu.springmvc.config;

import com.nhnacademy.edu.springmvc.Base;
import com.nhnacademy.edu.springmvc.repository.StudentRepository;
import com.nhnacademy.edu.springmvc.repository.StudentRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = Base.class,
        excludeFilters = {@ComponentScan.Filter(Controller.class)}
)
public class RootConfig {
    @Bean
    public StudentRepository studentRepository() {
        StudentRepository studentRepository = new StudentRepositoryImpl();
        studentRepository.register("테스트", "test.student@nhnacademy.com", 100, "훌륭");

        return studentRepository;
    }
}

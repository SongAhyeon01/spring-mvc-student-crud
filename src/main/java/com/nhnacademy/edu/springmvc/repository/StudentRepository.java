package com.nhnacademy.edu.springmvc.repository;

import com.nhnacademy.edu.springmvc.domain.Student;

import java.util.Map;

public interface StudentRepository {
    boolean exists(long id);

    Student register(String name, String email, int score, String comment);

    Student getStudent(long id);

    Map<Long, Student> getStudents();

    void modify(Student student);
}

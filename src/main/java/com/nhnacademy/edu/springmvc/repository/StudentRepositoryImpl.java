package com.nhnacademy.edu.springmvc.repository;

import com.nhnacademy.edu.springmvc.domain.Student;
import com.nhnacademy.edu.springmvc.exception.StudentNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Function;

@Slf4j
public class StudentRepositoryImpl implements StudentRepository {
    private final Map<Long, Student> studentMap = new HashMap<>();

    @Override
    public boolean exists(long id) {
        log.debug("i want to find : {}", id);
        boolean existResult = studentMap.containsKey(id);
        log.debug("is exist? : {}", existResult);
        return existResult;
    }

    @Override
    public Student register(String name, String email, int score, String comment) {
        long id = studentMap.keySet()
                .stream()
                .max(Comparator.comparing(Function.identity()))
                .map(l -> l + 1)
                .orElse(1L);

        Student student = Student.create(name, email);
        log.debug("my new id : {}", id);
        student.setId(id);
        student.setScore(score);
        student.setComment(comment);

        studentMap.put(id, student);

        return student;
    }

    @Override
    public Student getStudent(long id) {
        log.debug("getting student : {}", id);
        return exists(id) ? studentMap.get(id) : null;
    }

    @Override
    public Map<Long, Student> getStudents() {
        return studentMap;
    }

    @Override
    public void modify(Student student) {
        Student dbStudent = studentMap.get(student.getId());
        if (Objects.isNull(dbStudent)) {
            throw new StudentNotFoundException();
        }
        dbStudent.setName(student.getName());
        dbStudent.setEmail(student.getEmail());
        dbStudent.setScore(student.getScore());
        dbStudent.setComment(student.getComment());
        log.debug("modified student info : {}", dbStudent);
    }
}

package com.example.demo.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {



    private final List<Student> STUDENTS = Arrays.asList(
            new Student(0,"ali"),
            new Student(1,"hossein"),
            new Student(2,"ghader")
    );

    @RequestMapping("{studentID}")
    public Student getStudent(@PathVariable("studentID") Integer studentID){
        return STUDENTS.stream()
                .filter(student -> studentID.equals(student.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("student" + studentID + "not exist"));

    }

}

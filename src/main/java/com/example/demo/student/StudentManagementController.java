package com.example.demo.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
    Logger logger = LoggerFactory.getLogger(Student.class);

    private final List<Student> STUDENTS = Arrays.asList(
            new Student(0,"ali"),
            new Student(1,"hossein"),
            new Student(2,"ghader")
    );

 // hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')

    @RequestMapping(value = "/getAll" , method = RequestMethod.GET)
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN' , 'ROLE_ADMIN_READ')")
    public List<Student> getSTUDENTS(){
        System.out.println("all students get");
        return STUDENTS;

    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize(value = "hasAuthority('course:write')")
    public void RegisterStudents(@RequestBody Student student){
        System.out.println(student.getStudentName());
        //STUDENTS.add(student);
        //3logger.info("student id is :" + STUDENTS.get(student.getId()).getStudentName());

    }

    @RequestMapping(value = "/{studentId}" , method = RequestMethod.DELETE)
    @PreAuthorize(value = "hasAuthority('course:write')")
    public void deleteStudent( @PathVariable(value = "studentId") Integer studentId){
       // STUDENTS.remove(studentId);
        logger.info("deleted student id" + studentId);
    }

    @RequestMapping(value = "{studentId}" , method = RequestMethod.PUT )
    @PreAuthorize(value = "hasAuthority('course:write')")
    public void updateStudent(@PathVariable(value = "studentId") Integer studentId ,@RequestBody Student studentToUpdate){
        //STUDENTS.add(studentId,studentToUpdate);
        logger.info("student updated is :" + studentId);
    }
}

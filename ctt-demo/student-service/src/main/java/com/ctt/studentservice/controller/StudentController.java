package com.ctt.studentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctt.studentservice.request.CreateStudentRequest;
import com.ctt.studentservice.response.StudentResponse;
import com.ctt.studentservice.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping(path="/create")
    public StudentResponse createStudent (@RequestBody CreateStudentRequest createStudentRequest) {
        return studentService.createStudent(createStudentRequest);
    }

    @GetMapping(path="/{studentId}")
    public StudentResponse getStudentById (@PathVariable long studentId) {
        return studentService.getStudentById(studentId);
    }
}

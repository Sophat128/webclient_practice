package com.example.service;


import com.example.model.dto.StudentDto;
import com.example.model.request.StudentRequest;

import java.util.List;

public interface StudentService {

    StudentDto addStudent(StudentRequest studentRequest);

    List<StudentDto> getAllStudents();

    StudentDto getStudentById(Long id);

    StudentDto updateStudent(Long id, StudentRequest studentRequest);

    void deleteStudent(Long id);
}

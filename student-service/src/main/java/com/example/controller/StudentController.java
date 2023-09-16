package com.example.controller;

import com.example.exception.NotFoundExceptionClass;
import com.example.model.dto.StudentDto;
import com.example.model.request.StudentRequest;
import com.example.model.response.ApiResponse;
import com.example.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<?> getAllStudent() {
        var students = studentService.getAllStudents();
        return ResponseEntity.ok(ApiResponse.<List<StudentDto>>builder()
                .message("Get All Students Successfully")
                .payload(students)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        try {
            var student = studentService.getStudentById(id);
            return ResponseEntity.ok(new ApiResponse<>("Retrieve data Successfully", student));

        } catch (NotFoundExceptionClass e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(e.getMessage()));

        }

    }
    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody StudentRequest studentRequest) {
        try {
            var student = studentService.addStudent(studentRequest);
            return ResponseEntity.ok(new ApiResponse<>("Add student Successfully", student));

        } catch (NotFoundExceptionClass e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("This course code doesn't exist!!"));

        }


    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@RequestBody StudentRequest studentRequest, @PathVariable Long id) {
        try {
            var student = studentService.updateStudent(id, studentRequest);
            return ResponseEntity.ok(new ApiResponse<>("Update student Successfully", student));

        } catch (NotFoundExceptionClass e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(e.getMessage()));

        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok(new ApiResponse<>("Delete student Successfully"));

        } catch (NotFoundExceptionClass e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(e.getMessage()));

        }


    }
}

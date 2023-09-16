package com.example.controller;

import com.example.model.dto.CourseDto;
import com.example.model.request.CourseRequest;
import com.example.model.response.ApiResponse;
import com.example.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/course")
public class CourseController {
    private final CourseService studentService;

    @GetMapping("/{courseCode}")
    public CourseDto getCourseById(@PathVariable String courseCode) {
        return studentService.getCourseByCourseCode(courseCode);
    }

    @GetMapping
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.ok(new ApiResponse<>("Get All Courses",studentService.getAllCourses()));
    }

    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody CourseRequest courseRequest) {
        var course =  studentService.addCourse(courseRequest);
        return ResponseEntity.ok(new ApiResponse<>("Add course Successfully", course));

    }

    @DeleteMapping("/{courseCode}")
    public ResponseEntity<?> deleteCourse(@PathVariable String courseCode) {
        studentService.deleteCourse(courseCode);
        return ResponseEntity.ok(new ApiResponse<>("Deleted Successfully"));

    }

    @PutMapping("/{courseCode}")
    public ResponseEntity<?> updateCourse(@PathVariable String courseCode, @RequestBody CourseRequest courseRequest) {
       CourseDto courseDto =  studentService.updateCourse(courseCode, courseRequest);
        return ResponseEntity.ok(new ApiResponse<>("Course updated Successfully", courseDto));

    }



}

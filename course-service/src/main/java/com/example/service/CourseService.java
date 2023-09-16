package com.example.service;


import com.example.model.dto.CourseDto;
import com.example.model.request.CourseRequest;

import java.util.List;

public interface CourseService {

    CourseDto addCourse(CourseRequest courseRequest);

    CourseDto getCourseByCourseCode(String courseCode);

    List<CourseDto> getAllCourses();

    void deleteCourse(String courseCode);

    CourseDto updateCourse(String courseCode, CourseRequest courseRequest);
}

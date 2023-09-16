package com.example.service.serviceImpl;


import com.example.exception.NotFoundExceptionClass;
import com.example.model.dto.CourseDto;
import com.example.model.entity.Course;
import com.example.model.request.CourseRequest;
import com.example.repository.CourseRepository;
import com.example.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImp implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public CourseDto addCourse(CourseRequest courseRequest) {
        Course course = Course.builder()
                .courseName(courseRequest.getCourseName())
                .courseCode(courseRequest.getCourseCode())
                .description(courseRequest.getDescription())
                .instructor(courseRequest.getInstructor()).build();
        return courseRepository.save(course).toDto();
    }

    @Override
    public CourseDto getCourseByCourseCode(String courseCode) {
        return getCourse(courseCode).toDto();
    }

    private Course getCourse(String courseCode) {
        return courseRepository.findByCourseCode(courseCode)
                .orElseThrow(() -> new NotFoundExceptionClass("This course id doesn't exist!"));
    }

    @Override
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream().map(Course::toDto).toList();
    }

    @Override
    public void deleteCourse(String courseCode) {
        getCourseByCourseCode(courseCode);
        courseRepository.deleteByCourseCode(courseCode);
    }

    @Override
    public CourseDto updateCourse(String courseCode, CourseRequest courseRequest) {
        Course course = getCourse(courseCode);
        course.setCourseName(courseRequest.getCourseName());
        course.setCourseCode(courseRequest.getCourseCode());
        course.setDescription(courseRequest.getDescription());
        course.setInstructor(courseRequest.getInstructor());
        courseRepository.save(course);
        return course.toDto();
    }
}

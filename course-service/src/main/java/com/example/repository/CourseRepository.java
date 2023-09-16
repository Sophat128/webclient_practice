package com.example.repository;


import com.example.model.entity.Course;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCourseCode(String courseCode);

    void deleteByCourseCode(String courseCode);
}

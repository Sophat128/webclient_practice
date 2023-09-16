package com.example.model.entity;

import com.example.model.dto.CourseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String courseCode;
    private String description;
    private String instructor;

    public CourseDto toDto(){
        return new CourseDto(this.id, this.courseName, this.courseCode, this.description, this.instructor);
    }


}

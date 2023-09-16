package com.example.model.entity;

import com.example.model.dto.CourseDto;
import com.example.model.dto.StudentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String courseCode;




    public StudentDto toDto(CourseDto courseDto){
        return new StudentDto(this.id,this.firstName, this.lastName, this.email, LocalDateTime.now(),courseDto );
    }


}

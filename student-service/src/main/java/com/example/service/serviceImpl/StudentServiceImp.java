package com.example.service.serviceImpl;


import com.example.exception.NotFoundExceptionClass;
import com.example.model.dto.CourseDto;
import com.example.model.dto.StudentDto;
import com.example.model.entity.Student;
import com.example.model.request.StudentRequest;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImp implements StudentService {

    private final WebClient webClient = WebClient.create();
    private final StudentRepository studentRepository;

    @Override
    public StudentDto addStudent(StudentRequest studentRequest) {
        CourseDto courseDto = getCourseDto(studentRequest.getCourseCode());
        Student student = Student.builder()
                .firstName(studentRequest.getFirstName())
                .lastName(studentRequest.getLastName())
                .email(studentRequest.getEmail())
                .courseCode(studentRequest.getCourseCode())
                .build();
        return studentRepository.save(student).toDto(courseDto);
    }

    private CourseDto getCourseDto(String courseCode) {
        try {
        return webClient.get()
                .uri("http://localhost:8082/api/v1/course/" + courseCode)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.value() == 404,
                        error -> Mono.error(new NotFoundExceptionClass("error Body")))
                .bodyToMono(CourseDto.class)
                .block();
        }catch (NotFoundExceptionClass e){
            throw new NotFoundExceptionClass("This course doesn't exist!!");
        }
    }

    @Override
    public List<StudentDto> getAllStudents() {

        return studentRepository.findAll().stream().map(student -> student.toDto(getCourseDto(student.getCourseCode()))).toList();
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = getStudent(id);
        CourseDto courseDto = getCourseDto(student.getCourseCode());

        return student.toDto(courseDto);
    }

    private Student getStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionClass("This student id doesn't exist!"));
    }

    @Override
    public StudentDto updateStudent(Long id, StudentRequest studentRequest) {
        CourseDto courseDto = getCourseDto(studentRequest.getCourseCode());
        Student updateStudent = getStudent(id);
        updateStudent.setFirstName(studentRequest.getFirstName());
        updateStudent.setLastName(studentRequest.getLastName());
        updateStudent.setEmail(studentRequest.getEmail());
        updateStudent.setCourseCode(studentRequest.getCourseCode());
        studentRepository.save(updateStudent);
        return updateStudent.toDto(courseDto);
    }

    @Override
    public void deleteStudent(Long id) {
        getStudent(id);
        studentRepository.deleteById(id);
    }
}

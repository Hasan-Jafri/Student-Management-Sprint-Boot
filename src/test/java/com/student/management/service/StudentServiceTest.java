package com.student.management.service;

import com.student.management.model.Student;
import com.student.management.repository.StudentRepository;
import com.student.management.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStudent() {
        Student student = new Student("Hasan", "hasan@example.com", 19);
        when(studentRepository.save(student)).thenReturn(student);

        Student result = studentService.createStudent(student);

        assertThat(result.getName()).isEqualTo("Hasan");
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testGetAllStudents() {
        List<Student> mockStudents = Arrays.asList(
                new Student("Ali", "ali@mail.com",28),
                new Student("Sara", "sara@mail.com",11)
        );
        when(studentRepository.findAll()).thenReturn(mockStudents);

        List<Student> result = studentService.getAllStudents();

        assertThat(result).hasSize(2);
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testGetStudentById_Success() {
        Student student = new Student("Hasan", "hasan@example.com",32);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student result = studentService.getStudentById(1L);

        assertThat(result.getEmail()).isEqualTo("hasan@example.com");
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void testGetStudentById_NotFound() {
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> studentService.getStudentById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Student not found");
    }

}

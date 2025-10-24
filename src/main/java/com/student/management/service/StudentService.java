package com.student.management.service;


import com.student.management.model.Student;
import com.student.management.repository.StudentRepository;
import org.springframework.stereotype.Service;
import com.student.management.exception.ResourceNotFoundException;
import java.util.List;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student){
        if(studentRepository.existsByEmail(student.getEmail())){
            throw new  IllegalArgumentException("Email already exists");
        }
        return studentRepository.save(student);
    }


    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id){
        return studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student not found with IDL " + id));
    }

    public Student updateStudent(Long id, Student newStudent){
        Student existing = getStudentById(id);
        existing.setAge(newStudent.getAge());
        existing.setEmail(newStudent.getEmail());
        existing.setName(newStudent.getName());

        return studentRepository.save(existing);
    }

    public void deleteStudent(Long id){
        Student existing = getStudentById(id);
        studentRepository.delete(existing);
    }
}

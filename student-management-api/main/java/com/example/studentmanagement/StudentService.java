package com.example.student_management.service;

import java.util.List;
import com.example.student_management.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student_management.repository.StudentRepository;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id){
        return studentRepository.findById(id).orElse(null);
    }

    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails){
        Student student = studentRepository.findById(id).orElse(null);
        if(student != null){
            student.setFirstName(studentDetails.getFirstName());
            student.setLastName(studentDetails.getLastName());
            student.setEmail(studentDetails.getEmail());
            student.setAge(studentDetails.getAge());
            return studentRepository.save(student);
        }
        return null;
    }

    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }

}

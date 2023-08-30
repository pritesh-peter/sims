package com.sims.service;

import com.sims.model.Student;
import com.sims.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student save(Student student) {
        // Validate and save the student to the repository
        return studentRepository.save(student);
    }
    // other method implementations
}



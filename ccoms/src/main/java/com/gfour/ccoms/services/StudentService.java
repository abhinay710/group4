// StudentService.java

package com.gfour.ccoms.services;

import com.gfour.ccoms.dtos.StudentDTO;
import com.gfour.ccoms.entities.Student;
import com.gfour.ccoms.repositories.StudentRepo;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ModelMapper modelMapper;

    public List<StudentDTO> getAllStudents() {
        Iterable<Student> students = studentRepo.findAll();
        List<StudentDTO> studentDTOList = new ArrayList<>();
        students.forEach(student -> {
            studentDTOList.add(modelMapper.map(student, StudentDTO.class));
        });

        return studentDTOList;
    }

    public StudentDTO findByStudentId(Integer studentId) {
        Optional<Student> student = studentRepo.findById(studentId);

        return modelMapper.map(student.get(), StudentDTO.class);
    }
}


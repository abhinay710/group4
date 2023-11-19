package com.gfour.ccoms.controllers;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gfour.ccoms.dtos.StudentDTO;

import com.gfour.ccoms.entities.Student;
import com.gfour.ccoms.repositories.StudentRepo;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/all")
    public List<StudentDTO> getAll() {
        Iterable<Student>  student = studentRepo.findAll();
        List<StudentDTO> ordersList = new ArrayList<>();
        student.forEach(item -> {
            ordersList.add(modelMapper.map(item, StudentDTO.class));
        });

        return ordersList;
    }
}
package com.gfour.ccoms.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gfour.ccoms.dtos.OrdersDTO;
import com.gfour.ccoms.dtos.StudentDTO;

import com.gfour.ccoms.entities.Student;
import com.gfour.ccoms.repositories.StudentRepo;
import com.gfour.ccoms.services.StudentService;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
     private StudentService studentService;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/all")
    public List<StudentDTO> getAll() {
        Iterable<Student>  student = studentRepo.findAllByOrderByIdDesc();
        List<StudentDTO> ordersList = new ArrayList<>();
        student.forEach(item -> {
            ordersList.add(modelMapper.map(item, StudentDTO.class));
        });

        return ordersList;
    }

    @GetMapping("/{id}")
    public StudentDTO getByStudentId(@PathVariable Integer id) {
        return studentService.findByStudentId(id);
}

    @PostMapping("/save")
    public StudentDTO saveStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.save(studentDTO);
    }

}
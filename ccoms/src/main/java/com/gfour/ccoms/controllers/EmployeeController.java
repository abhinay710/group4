package com.gfour.ccoms.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gfour.ccoms.dtos.EmployeeDTO;

import com.gfour.ccoms.entities.Employee;
import com.gfour.ccoms.repositories.EmployeeRepo;
import com.gfour.ccoms.services.EmployeeService;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public List<EmployeeDTO> getAll() {
        Iterable<Employee>  employee = employeeRepo.findAll();
        List<EmployeeDTO> ordersList = new ArrayList<>();
        employee.forEach(item -> {
            ordersList.add(modelMapper.map(item, EmployeeDTO.class));
        });

        return ordersList;
    }

    @PostMapping("/save")
    public EmployeeDTO saveEmp(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.save(employeeDTO);
    }
}
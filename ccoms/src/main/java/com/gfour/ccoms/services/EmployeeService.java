package com.gfour.ccoms.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfour.ccoms.dtos.EmployeeDTO;
import com.gfour.ccoms.entities.Employee;
import com.gfour.ccoms.repositories.EmployeeRepo;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    ModelMapper modelMapper;

    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employeeRepo.save(employee);

        return modelMapper.map(employee, EmployeeDTO.class);
    }
}

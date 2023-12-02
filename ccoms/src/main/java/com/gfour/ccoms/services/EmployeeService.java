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

    public EmployeeDTO save(EmployeeDTO employeeDTO) throws Exception {
        if (employeeDTO.getId() == null && Boolean.TRUE.equals(employeeRepo.existsByEmailID(employeeDTO.getEmailID()))) {
            throw new Exception("user already exists with the same user name");
        }
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employeeRepo.save(employee);

        return modelMapper.map(employee, EmployeeDTO.class);
    }
}

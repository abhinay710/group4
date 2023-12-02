package com.gfour.ccoms.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gfour.ccoms.entities.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Integer> {
    Employee findByEmailIDAndPassword(String emailId, String password);
    List<Employee> findAllByOrderByIdDesc();
    boolean existsByEmailID(String emailId);
}

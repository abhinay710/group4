package com.gfour.ccoms.repositories;

import org.springframework.data.repository.CrudRepository;

import com.gfour.ccoms.entities.Orders;
import com.gfour.ccoms.entities.Student;

public interface OrdersRepo extends CrudRepository<Orders, Integer> {
    Iterable<Orders> findByStudent(Student studnet);
}

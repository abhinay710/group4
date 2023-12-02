package com.gfour.ccoms.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gfour.ccoms.entities.Student;

public interface StudentRepo extends CrudRepository<Student, Integer> {
    Student findByGlobalIDAndPassword(String globalId, String password);
    Boolean existsByGlobalID(String globalId);
    List<Student> findAllByOrderByIdDesc();

}

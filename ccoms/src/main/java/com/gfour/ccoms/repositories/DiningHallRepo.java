package com.gfour.ccoms.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gfour.ccoms.entities.DiningHall;

public interface DiningHallRepo extends CrudRepository<DiningHall, Integer> {
    List<DiningHall> findAllByOrderByIdDesc();
}

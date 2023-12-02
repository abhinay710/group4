package com.gfour.ccoms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gfour.ccoms.entities.Menu;

public interface MenuRepo extends JpaRepository<Menu, Integer> {
    List<Menu> findByDiningHallId(Integer diningHallId);
    List<Menu> findAllByOrderByIdDesc();
}

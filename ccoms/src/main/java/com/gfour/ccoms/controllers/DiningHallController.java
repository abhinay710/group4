package com.gfour.ccoms.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfour.ccoms.dtos.DiningHallDTO;
import com.gfour.ccoms.entities.DiningHall;
import com.gfour.ccoms.entities.Menu;

import com.gfour.ccoms.mapper.OrdersMapper;
import com.gfour.ccoms.repositories.DiningHallRepo;
import com.gfour.ccoms.repositories.MenuRepo;

@RestController
@RequestMapping("dining-hall")
public class DiningHallController {
    @Autowired
    private DiningHallRepo diningHallRepo;

    @GetMapping("/all")
    public List<DiningHallDTO> getAll() {
        Iterable<DiningHall>  diningHalls = diningHallRepo.findAll();
        List<DiningHallDTO> diningHallList = new ArrayList<>();
        diningHalls.forEach(item -> {
            diningHallList.add(OrdersMapper.INSTANCE.diningHallToDiningHallDTO(item));
        });

        return diningHallList;
    }
}

package com.gfour.ccoms.controllers;


import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfour.ccoms.dtos.DiningHallDTO;

import com.gfour.ccoms.entities.DiningHall;

import com.gfour.ccoms.mapper.OrdersMapper;
import com.gfour.ccoms.repositories.DiningHallRepo;
import com.gfour.ccoms.services.DiningHallService;


@RestController
@RequestMapping("dining-hall")
public class DiningHallController {
    @Autowired
    private DiningHallRepo diningHallRepo;
    @Autowired
    private DiningHallService diningHallService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<DiningHallDTO> getAll() {
        List<DiningHall>  diningHalls = diningHallRepo.findAllByOrderByIdDesc();
        List<DiningHallDTO> diningHallList = new ArrayList<>();
        diningHalls.forEach(item -> {
            diningHallList.add(modelMapper.map(item, DiningHallDTO.class));
        });

        return diningHallList;
    }

    @PostMapping("/save")
    public DiningHallDTO saveDiningHall(@RequestBody DiningHallDTO diningHallDTO) {
        return diningHallService.save(diningHallDTO);
    }
}

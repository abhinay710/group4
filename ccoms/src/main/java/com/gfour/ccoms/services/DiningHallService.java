package com.gfour.ccoms.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfour.ccoms.dtos.DiningHallDTO;
import com.gfour.ccoms.entities.DiningHall;
import com.gfour.ccoms.repositories.DiningHallRepo;

@Service
public class DiningHallService {
    @Autowired
    DiningHallRepo diningHallRepo;
    @Autowired
    ModelMapper modelMapper;

    public DiningHallDTO save(DiningHallDTO diningHallDTO) {
        DiningHall diningHall = modelMapper.map(diningHallDTO, DiningHall.class);
        diningHallRepo.save(diningHall);

        return modelMapper.map(diningHall, DiningHallDTO.class);
    }
}

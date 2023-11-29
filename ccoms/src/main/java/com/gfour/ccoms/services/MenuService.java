package com.gfour.ccoms.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfour.ccoms.dtos.MenuDTO;
import com.gfour.ccoms.entities.Menu;
import com.gfour.ccoms.repositories.MenuRepo;

@Service
public class MenuService {
    @Autowired
    MenuRepo menuRepo;
    @Autowired
    ModelMapper modelMapper;

    public MenuDTO save(MenuDTO menuDTO) {
        Menu menu = modelMapper.map(menuDTO, Menu.class);
        menuRepo.save(menu);

        return modelMapper.map(menu, MenuDTO.class);
    }
}

package com.gfour.ccoms.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfour.ccoms.dtos.MenuDTO;
import com.gfour.ccoms.entities.Menu;
import com.gfour.ccoms.repositories.MenuRepo;
import com.gfour.ccoms.services.MenuService;

@RestController
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuRepo menuRepo;
    @Autowired
    private MenuService menuService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/all")
    public List<MenuDTO> getAll() {
        Iterable<Menu>  menuItems = menuRepo.findAll();
        List<MenuDTO> menuList = new ArrayList<>();
        menuItems.forEach(item -> {
            menuList.add(modelMapper.map(item, MenuDTO.class));
        });

        return menuList;
    }

    @GetMapping("/dining-hall/{id}")
    public List<MenuDTO> findByDiningHall(@PathVariable Integer id) {
        Iterable<Menu>  menuItems = menuRepo.findByDiningHallId(id);
        List<MenuDTO> menuList = new ArrayList<>();
        menuItems.forEach(item -> {
            menuList.add(modelMapper.map(item, MenuDTO.class));
        });

        return menuList;
    }


    @PostMapping("/save")
    public MenuDTO saveMenu(@RequestBody MenuDTO menuDTO) {
        return menuService.save(menuDTO);
    }
}

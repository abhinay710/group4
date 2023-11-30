package com.gfour.ccoms.controllers;

import java.util.ArrayList;
import java.util.List;

import com.gfour.ccoms.entities.Menu;
import com.gfour.ccoms.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gfour.ccoms.dtos.OrdersDTO;
import com.gfour.ccoms.entities.Orders;
import com.gfour.ccoms.mapper.OrdersMapper;
import com.gfour.ccoms.repositories.OrdersRepo;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrdersRepo ordersRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public List<OrdersDTO> getAll() {
        Iterable<Orders>  ordersList = ordersRepo.findAll();
        List<OrdersDTO> ordersDTOList = new ArrayList<>();
        ordersList.forEach(order -> {
            ordersDTOList.add(OrdersMapper.INSTANCE.ordersToOrdersDTO(order));
        });
        ordersDTOList.sort((o1, o2) -> {
            return o2.getId().compareTo(o1.getId());
        });

        return ordersDTOList;
    }

    @GetMapping("/dining-hall/{id}")
    public List<OrdersDTO> findByDiningHall(@PathVariable Integer id) {
        Iterable<Orders>  ordersList = ordersRepo.findByDiningHallId(id);
        List<OrdersDTO> ordersDTOList = new ArrayList<>();
        ordersList.forEach(order -> {
            ordersDTOList.add(OrdersMapper.INSTANCE.ordersToOrdersDTO(order));
        });

        ordersDTOList.sort((o1, o2) -> {
            return o2.getId().compareTo(o1.getId());
        });

        return ordersDTOList;
    }

    @GetMapping("/student/{id}")
    public List<OrdersDTO> getByStudentId(@PathVariable Integer id) {
        return orderService.findByStudentId(id);
    }

    @PostMapping("update-status/{id}/{status}")
    public void updateOrderStatus(@PathVariable Integer id, @PathVariable String status) {
        orderService.updateOrderStatus(id, status);
    }


    @PostMapping("place-order")
    public void updateOrderStatus(@RequestBody OrdersDTO orderDTO) {
        orderService.placeOrder(orderDTO);
    }
}

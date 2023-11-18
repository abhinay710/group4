package com.gfour.ccoms.services;

import com.gfour.ccoms.dtos.OrdersDTO;
import com.gfour.ccoms.dtos.OrdersDetailsDTO;
import com.gfour.ccoms.entities.DiningHall;
import com.gfour.ccoms.entities.Employee;
import com.gfour.ccoms.entities.Menu;
import com.gfour.ccoms.entities.Orders;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfour.ccoms.entities.OrdersDetails;
import com.gfour.ccoms.entities.Student;
import com.gfour.ccoms.repositories.OrdersRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrdersRepo ordersRepo;
    @Autowired
    private ModelMapper modelMapper;
    
    public void updateOrderStatus(Integer id, String status) {
        Optional<Orders> order = ordersRepo.findById(id);

        if (order.isPresent()) {
            Orders orders = order.get();
            orders.setOrdersStatus(status);
            ordersRepo.save(orders);
        }
    }

    @Transactional
    public void placeOrder(OrdersDTO ordersDTO) {
        Orders order = convertToOrdersEntity(ordersDTO);
        order.setCreatedOn(new Date());
        List<OrdersDetails> ordersDetails = convertToOrderDetailsEntity(ordersDTO.getOrdersDetails());
        ordersDetails.forEach(ordersDetail -> {
            ordersDetail.setOrders(order);
        });
        order.setOrderDetails(ordersDetails);

        ordersRepo.save(order);
    }

    private Orders convertToOrdersEntity(OrdersDTO ordersDTO) {
        Orders order = new Orders();
        order.setStudent(new Student(ordersDTO.getStudentID()));
        order.setEmployee(new Employee(ordersDTO.getEmployeeID()));
        order.setDiningHall(new DiningHall(ordersDTO.getDiningHallID()));
        order.setTotalAmount(ordersDTO.getTotalAmount());
        order.setOrdersStatus(ordersDTO.getOrdersStatus());
        order.setCreatedBy(ordersDTO.getCreatedBy());
        order.setUpdatedBy(ordersDTO.getUpdatedBy());
        return order;
    }

    private List<OrdersDetails> convertToOrderDetailsEntity(List<OrdersDetailsDTO>  ordersDetailsDTOList) {
        List<OrdersDetails> ordersDetailsList = new ArrayList<>();
        ordersDetailsDTOList.forEach(ordersDetailsDTO -> {
            OrdersDetails ordersDetails = new OrdersDetails();
            ordersDetails.setMenu(new Menu(ordersDetailsDTO.getMenuID()));
            ordersDetails.setPrice(ordersDetailsDTO.getPrice());
            ordersDetails.setQuantity(ordersDetailsDTO.getQuantity());
            ordersDetailsList.add(ordersDetails);
        });

        return ordersDetailsList;
    }
}
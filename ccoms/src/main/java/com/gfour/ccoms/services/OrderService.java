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
import com.gfour.ccoms.mapper.OrdersMapper;
import com.gfour.ccoms.repositories.OrdersRepo;
import com.gfour.ccoms.repositories.StudentRepo;

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
    private StudentRepo studentRepo;
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
        Student student = studentRepo.findById(ordersDTO.getStudentID()).get();
        Orders order = OrdersMapper.INSTANCE.ordersDTOToOrders(ordersDTO);
        order.setCreatedBy(student.getFirstName() + " " + student.getLastName());
        order.setEmployee(null);
        order.setCreatedOn(new Date());

        order.getOrdersDetails().forEach(ordersDetails -> {
            ordersDetails.setOrders(order);
        });

        if (student.getFlexAmountBalance() >= order.getTotalAmount()) {
            student.setFlexAmountBalance(student.getFlexAmountBalance() - order.getTotalAmount());

            ordersRepo.save(order);
            studentRepo.save(student);
        } else {
            throw new RuntimeException("insufficient balance");
        }
    }


    public List<OrdersDTO> findByStudentId(Integer studentId) {
        Student student = new Student();
        student.setId(studentId);
        Iterable<Orders> ordersIterable = ordersRepo.findByStudent(student);
        List<OrdersDTO> ordersList = new ArrayList<>();

        ordersIterable.forEach(order -> {
            ordersList.add(OrdersMapper.INSTANCE.ordersToOrdersDTO(order));
        });

        ordersList.sort((o1, o2) -> {
            return o2.getId().compareTo(o1.getId());
        });

        return ordersList;
    }
}
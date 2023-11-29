package com.gfour.ccoms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import java.util.List;

import com.gfour.ccoms.dtos.DiningHallDTO;
import com.gfour.ccoms.dtos.MenuDTO;
import com.gfour.ccoms.dtos.OrdersDTO;
import com.gfour.ccoms.dtos.OrdersDetailsDTO;
import com.gfour.ccoms.entities.DiningHall;
import com.gfour.ccoms.entities.Menu;
import com.gfour.ccoms.entities.Orders;
import com.gfour.ccoms.entities.OrdersDetails;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrdersMapper {
    OrdersMapper INSTANCE = Mappers.getMapper(OrdersMapper.class);

    @Mapping(source = "student.id", target = "studentID")
    @Mapping(source = "employee.id", target = "employeeID")
    @Mapping(target = "diningHall.menuItems", ignore = true)
    @Mapping(target = "diningHall.orders", ignore = true)
    @Mapping(target = "diningHall.employees", ignore = true)
    OrdersDTO ordersToOrdersDTO(Orders orders);

    List<OrdersDTO> ordersListToOrdersDTOList(List<Orders> ordersList);

    @Mapping(source = "ordersDTO.studentID", target = "student.id")
    @Mapping(source = "ordersDTO.employeeID", target = "employee.id")
    Orders ordersDTOToOrders(OrdersDTO ordersDTO);

    @Mapping(source = "orders.id", target = "ordersID")
    OrdersDetailsDTO ordersDetailsToOrdersDetailsDTO(OrdersDetails ordersDetails);

    List<OrdersDetailsDTO> ordersDetailsListToOrdersDetailsDTOList(List<OrdersDetails> ordersDetailsList);

    @Mapping(source = "ordersDetailsDTO.ordersID", target = "orders.id")
    @Mapping(source = "ordersDetailsDTO.menuID", target = "menu.id")
    OrdersDetails ordersDetailsDTOToOrdersDetails(OrdersDetailsDTO ordersDetailsDTO);
}

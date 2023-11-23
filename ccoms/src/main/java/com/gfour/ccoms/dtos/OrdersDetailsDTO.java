package com.gfour.ccoms.dtos;

import lombok.Data;

@Data
public class OrdersDetailsDTO {
    private Integer ordersID;
    private Integer menuID;
    private MenuDTO menu;
    private Integer quantity;
    private Double price;
}

package com.gfour.ccoms.dtos;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrdersDTO {
    private int id;
    private int studentID;
    private int employeeID;
    private int diningHallID;
    private Double totalAmount;
    private String ordersStatus;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private List<OrdersDetailsDTO> ordersDetails;
}

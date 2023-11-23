package com.gfour.ccoms.dtos;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrdersDTO {
    private Integer id;
    private Integer studentID;
    private Integer employeeID;
    private Integer diningHallID;
    private Double totalAmount;
    private String ordersStatus;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private List<OrdersDetailsDTO> ordersDetails;
}

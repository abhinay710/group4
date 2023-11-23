package com.gfour.ccoms.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class MenuDTO {
    private Integer id;
    private String itemName;
    private Double price;
    private String diningStation;
    private String portion;
    private Integer calories;
    private String ingredients;
    private Character isVegan;
    private Character containsGluten;
    private Character containsDairy;
    private Character containsNuts;
    private Double protein;
    private Double totalCarbohydrates;
    private Double cholesterol;
    private Double totalFat;
    private Double sodium;
    private Double potassium;
    private Double iron;
    private Double calcium;
    private Integer diningHallID;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
}

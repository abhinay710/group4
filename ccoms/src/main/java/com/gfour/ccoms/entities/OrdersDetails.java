package com.gfour.ccoms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "OrdersDetails")
@RequiredArgsConstructor
@NoArgsConstructor
public class OrdersDetails {
    @Id
    @ManyToOne
    @JoinColumn(name = "OrdersID")
    @NonNull
    private Orders orders;

    @Id
    @ManyToOne
    @JoinColumn(name = "MenuID")
    private Menu menu;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Price")
    private Double price;

}

package com.example.finalproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Setter
@Getter
@RequiredArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double totalPrice;
    private int newPoint;
    private String status;//new compleate progress
    private String payment;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "orderId",referencedColumnName = "id")
    private Company company ;
}

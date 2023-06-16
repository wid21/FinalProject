package com.example.finalproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@Setter
@Getter
@RequiredArgsConstructor

public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double totalprice;
    private int newpoints;
    private String status;


    @OneToOne(cascade = CascadeType.ALL,mappedBy = "booking")
    @PrimaryKeyJoinColumn
    private ReservationDate date;

    @ManyToOne
    @JsonIgnore
    private Details details;

    @ManyToOne
    @JsonIgnore
    private Customer customer;



    @OneToOne
    @PrimaryKeyJoinColumn
    private Rate rate ;


}

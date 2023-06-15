package com.example.finalproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@Setter
@Getter
@RequiredArgsConstructor
public class Details {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;
    private String category;
    private int num_service;
    private String description;
    private int hours;
    private double price;



    @ManyToOne
    @JsonIgnore
    private Company company;



    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "details")
    private Set<Booking> bookings;

//    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "details")
//
//    private Set<ReservationDate> reservationDates;






}

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
public class Customer {


    @Id
    private Integer id;
    private String phoneNumber;
    private String address;
    private int points;




    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser myUser;


    @OneToMany(mappedBy = "customer",cascade = CascadeType.DETACH )
    @PrimaryKeyJoinColumn
    private Set<Booking> bookings;



}

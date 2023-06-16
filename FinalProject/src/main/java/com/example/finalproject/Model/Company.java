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
public class Company {

    @Id
    private Integer id;
    private String name;
    private String phoneNumber;
    private String address;
    private String licensNum;
    private String status;



    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser myUser;


    @OneToMany(mappedBy = "company",cascade = CascadeType.DETACH )
    @PrimaryKeyJoinColumn
    private Set<Staff> staff;

    @OneToMany(mappedBy = "company",cascade = CascadeType.DETACH)
    @PrimaryKeyJoinColumn
    private Set<Details> details;


}

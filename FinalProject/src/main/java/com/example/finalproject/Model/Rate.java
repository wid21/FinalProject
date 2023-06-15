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
public class Rate {
    @Id
    private Integer id;
    private Integer rate ;
    private String review ;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Booking booking;

}

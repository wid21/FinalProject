package com.example.finalproject.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Setter
@Getter
@RequiredArgsConstructor
public class ReservationDate {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime reservationDate;

    //private Integer detailsId;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Booking booking;

    @ManyToOne
    @JsonIgnore
    private Details details ;


}

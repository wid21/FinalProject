package com.example.finalproject.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DtoRate {
    private Integer bookingId;
    private Integer customerId;
    private Integer rate ;
    private String review ;
}

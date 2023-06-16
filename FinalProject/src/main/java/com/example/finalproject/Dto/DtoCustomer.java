package com.example.finalproject.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class DtoCustomer {
    private Integer cartId;
    private String username;
    private String  email;
    private String  password;
    private String role;
    private String phoneNumber;
    private String address;
    //private int  points;
}

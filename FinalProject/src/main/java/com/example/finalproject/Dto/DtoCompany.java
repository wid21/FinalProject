package com.example.finalproject.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DtoCompany {


    private String username;
    private String  email;
    private String  password;
    private String role;
    private String status;
    private String name;
    private String phoneNumber;
    private String address;
    private String licensNum;

}

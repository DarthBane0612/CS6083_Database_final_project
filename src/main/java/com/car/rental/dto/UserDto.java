package com.car.rental.dto;

import lombok.Data;

@Data
public class UserDto {

    private String customerId;
    private String fname;
    private String mname;
    private String lname;
    private String dlNum;
    private String inCompany;
    private String policy;
    private String password;
    private String username;

    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String phone;
    private String email;
    private String type;
}

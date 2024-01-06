package com.car.rental.pojo;


import lombok.Data;

@Data
public class CustomerPojo {
    private String customerId;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String phone;
    private String email;
    private String type;


}

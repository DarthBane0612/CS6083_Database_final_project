package com.car.rental.dto;

import lombok.Data;

@Data
public class RentalServiceDto {

    private String serviceId;
    private String sOdometer;
    private String eOdometer;
    private String limit;
    private String customerId;
    private String vin;
    private String rentalRate;

    private String overMileageFee;
}

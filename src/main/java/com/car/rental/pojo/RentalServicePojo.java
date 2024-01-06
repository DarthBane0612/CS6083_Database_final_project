package com.car.rental.pojo;

import lombok.Data;

@Data
public class RentalServicePojo {
    private String serviceId;
    private String sOdometer;
    private String eOdometer;
    private String limit;
    private String customerId;
    private String vin;
    private String rentalRate;

    private String overMileageFee;
}

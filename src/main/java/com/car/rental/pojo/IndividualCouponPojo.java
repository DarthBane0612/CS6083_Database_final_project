package com.car.rental.pojo;

import lombok.Data;

@Data
public class IndividualCouponPojo {


    private String couponId;
    private String validFrom;
    private String validTo;
    private String discount;
    private String type;
    private String serviceId;

}

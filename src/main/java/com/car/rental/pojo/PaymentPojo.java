package com.car.rental.pojo;

import lombok.Data;

@Data
public class PaymentPojo {

    private String paymentId;
    private String paymentDate;
    private String cardNum;
    private String invoceInvoceId;
    private String paymentMethod;
}

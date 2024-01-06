package com.car.rental.dto;

import lombok.Data;

@Data
public class PaymentDto {

    private String paymentId;
    private String paymentDate;
    private String cardNum;
    private String invoceInvoceId;
    private String paymentMethod;

    private String paymentMsg;
}

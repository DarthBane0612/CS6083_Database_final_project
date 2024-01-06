package com.car.rental.dto;

import lombok.Data;

@Data
public class InvoiceDto {

    private String invoiceId;
    private String invoiceDate;
    private String invoiceAmount;
    private String serviceId;

    private String customerId;

}

package com.car.rental.pojo;

import lombok.Data;

@Data
public class InvoicePojo {

    private String invoiceId;
    private String invoiceDate;
    private String invoiceAmount;
    private String serviceId;
    private String customerId;

}

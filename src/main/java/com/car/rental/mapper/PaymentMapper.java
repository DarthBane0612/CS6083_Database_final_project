package com.car.rental.mapper;

import com.car.rental.pojo.PaymentPojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {


    PaymentPojo selectPaymentByInvoiced(String invoiceId);

    int insertPayment(String paymentId, String cardType, String cardNum,String invoiceId);
}

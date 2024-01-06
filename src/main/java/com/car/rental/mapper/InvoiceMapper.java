package com.car.rental.mapper;

import com.car.rental.pojo.InvoicePojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InvoiceMapper {
    int insertInvoice(String invoiceId, String serviceId, String invoiceAmount);

    List<InvoicePojo> selectInvoiceByCustomerId(String customerId);
}

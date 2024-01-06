package com.car.rental.service;


import com.car.rental.dto.*;

import java.util.List;

public interface RentService {
    List<OfficeDto> getOffice();


    List<VehiclesDto> getVehicles(String officeId);

    int createService(String customerId, String vin, String serviceId,String sOdometer,String type);

    List<RentalServiceDto> getService(String customerId);

    int createInvoice(String serviceId,String type,String rentalRate,String overMileageFee);

    List<InvoiceDto> getInvoice(String customerId, String type);

    String getPay(String invoiceId, String invoiceAmount, String cardType, String cardNum);

    PaymentDto getPayDetail(String invoiceId);
}

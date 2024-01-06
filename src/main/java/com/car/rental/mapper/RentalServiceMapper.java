package com.car.rental.mapper;


import com.car.rental.pojo.RentalServicePojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RentalServiceMapper {




    int createService(String customerId, String vin, String serviceId, String sOdometer);

    List<RentalServicePojo> getService(String customerId);


    RentalServicePojo selectSodometerByserviceId(String serviceId);

    void updateEodometerByserviceId(String serviceId, String eOdometer);
}

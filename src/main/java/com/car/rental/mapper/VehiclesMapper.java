package com.car.rental.mapper;

import com.car.rental.pojo.VehiclesPojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VehiclesMapper {


    List<VehiclesPojo> getVehicles(String officeId);


    String selectsOdometerByVin(String vin);
}

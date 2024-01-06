package com.car.rental.mapper;

import com.car.rental.dto.UserDto;
import com.car.rental.pojo.CorporatePojo;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface CorporateMapper {
    CorporatePojo getCorporate(UserDto userDto);

    int insertCorporate(UserDto userDto);

    CorporatePojo getCorporate(String username, String password);

    CorporatePojo selectDiscount(String serviceId, String type);

}

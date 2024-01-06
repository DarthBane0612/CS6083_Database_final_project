package com.car.rental.mapper;

import com.car.rental.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {


    int insert(UserDto userDto);
}

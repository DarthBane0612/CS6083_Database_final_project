package com.car.rental.mapper;

import com.car.rental.dto.UserDto;
import com.car.rental.pojo.IndividualPojo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface IndividualMapper {

    IndividualPojo getIndividual(UserDto userDto);

    int insertIndividual(UserDto userDto);
}

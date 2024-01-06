package com.car.rental.mapper;

import com.car.rental.pojo.OfficePojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OfficeMapper {
    List<OfficePojo> getOffice();
}

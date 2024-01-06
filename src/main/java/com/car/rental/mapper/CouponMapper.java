package com.car.rental.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponMapper {


    int createCoupon(String couponId, String type, String serviceId);
}

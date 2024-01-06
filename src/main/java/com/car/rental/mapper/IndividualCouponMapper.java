package com.car.rental.mapper;

import com.car.rental.pojo.IndividualCouponPojo;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface IndividualCouponMapper {
    int individualCoupon(String couponId, String discount);

    IndividualCouponPojo selectDiscount(String serviceId, String type);
}

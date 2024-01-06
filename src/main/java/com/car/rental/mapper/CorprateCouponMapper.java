package com.car.rental.mapper;

import com.car.rental.pojo.CorprateCouponPojo;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface CorprateCouponMapper {

    int individualCoupon(String couponId, String discount);

    CorprateCouponPojo selectDiscount(String serviceId, String type);
}

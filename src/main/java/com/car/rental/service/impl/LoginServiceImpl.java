package com.car.rental.service.impl;

import com.car.rental.dto.UserDto;
import com.car.rental.mapper.CorporateMapper;
import com.car.rental.mapper.CustomerMapper;
import com.car.rental.mapper.IndividualMapper;
import com.car.rental.pojo.CorporatePojo;
import com.car.rental.pojo.IndividualPojo;
import com.car.rental.service.LoginService;
import com.car.rental.utils.BeanCopyUtils;
import com.car.rental.utils.GetIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private IndividualMapper individualMapper;

    @Autowired
    private CorporateMapper corporateMapper;

    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public UserDto login(UserDto userDto) {
        UserDto userDto1 = new UserDto();
        if(userDto.getType().equals("2")){
            IndividualPojo individual = individualMapper.getIndividual(userDto);
            userDto1 = BeanCopyUtils.copyBean(individual,UserDto.class);
        }
        if(userDto.getType().equals("1")){
           CorporatePojo corporatePojo =  corporateMapper.getCorporate(userDto.getUsername(),userDto.getPassword());
            userDto1 = BeanCopyUtils.copyBean(corporatePojo,UserDto.class);
        }
        return userDto1;
    }

    @Override
    @Transactional
    public void registered(UserDto userDto) {
        userDto.setCustomerId(GetIdUtils.verificationCode());
        int num = customerMapper.insert(userDto);
        if(userDto.getType().equals("2")){
           int nums =  individualMapper.insertIndividual(userDto);
        }
        if(userDto.getType().equals("1")){
            int numss = corporateMapper.insertCorporate(userDto);
        }
    }
}

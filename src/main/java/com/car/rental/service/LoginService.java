package com.car.rental.service;


import com.car.rental.dto.UserDto;

public interface LoginService {
    UserDto  login(UserDto userDto);

    void registered(UserDto userDto);
}

package com.car.rental.controller;

import com.car.rental.dto.UserDto;
import com.car.rental.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public String loginPage(){
        return "login.html";
    }

    @RequestMapping ("/index.html")
    public String index(){
        return "index.html";
    }
    @RequestMapping ("/rent")
    public String rent(){
        return "rent.html";
    }

    @RequestMapping ("/service")
    public String service(){
        return "service.html";
    }

    @RequestMapping ("/rentCart")
    public String rentCart(){
        return "rent_cart.html";
    }

    @RequestMapping ("/pay")
    public String pay(){
        return "pay.html";
    }

    @RequestMapping ("/registered")
    public String registered(){
        return "registered.html";
    }

    @RequestMapping ("user/login")
    public void login(UserDto userDto, HttpSession session,
                      RedirectAttributes redirectAttributes, HttpServletResponse response){
        System.out.println("Type--->"+userDto.getType());
        UserDto userDto1  = loginService.login(userDto);
        if(userDto1.getCustomerId() != null ) {
            session.setAttribute("loginUser", userDto1);
        }

    }

    @RequestMapping ("user/registered")
    public String registered(UserDto userDto,
                             RedirectAttributes redirectAttributes, HttpServletResponse response){
        System.out.println(userDto.toString());
        loginService.registered(userDto);
        return "login.html";

    }




}

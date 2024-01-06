package com.car.rental.controller;


import com.car.rental.dto.*;
import com.car.rental.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rent")
public class RentController {


    @Autowired
    private RentService rentService;



    @RequestMapping("/getOffice")
    public Map<String, Object> getOffice(){
        List<OfficeDto> data = rentService.getOffice();
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("code", 0);
        res.put("msg", "");
        return res;
    }

    @RequestMapping("/getVehicles")
    public Map<String,Object> getVehicles(String officeId){
        System.out.println("officeId==>"+officeId);
        List<VehiclesDto> carDtos = rentService.getVehicles(officeId);
        Map<String, Object> res = new HashMap<>();
        res.put("data", carDtos);
        res.put("code", 0);
        res.put("msg", "");
        return res;
    }

    @RequestMapping("/createService")
    public String createService(HttpServletRequest request,String vin,String serviceId,String sOdometer){
        HttpSession session = request.getSession();
        UserDto loginUser = (UserDto)session.getAttribute("loginUser");
        System.out.println(vin);
        System.out.println(serviceId);
        System.out.println(loginUser.toString());
        int service = rentService.createService(loginUser.getCustomerId(), vin,
                serviceId.substring(7, 13), sOdometer,loginUser.getType());
        return service+"";
    }

    @RequestMapping("/getService")
    public Map<String,Object> getService(HttpServletRequest request){
        HttpSession session = request.getSession();
        UserDto loginUser = (UserDto)session.getAttribute("loginUser");
        List<RentalServiceDto> rentalServiceDtos = rentService.getService(loginUser.getCustomerId());
        Map<String, Object> res = new HashMap<>();
        res.put("data", rentalServiceDtos);
        res.put("code", 0);
        res.put("msg", "");
        return res;
    }

    @RequestMapping("/createServiceId")
    public String createServiceId(){
        String serviceId = System.currentTimeMillis()+"";
        System.out.println(serviceId);
        return serviceId;
    }

    @RequestMapping("/createInvoice")
    public String createInvoice(HttpServletRequest request,String serviceId,String rentalRate,String overMileageFee){
        HttpSession session = request.getSession();
        UserDto loginUser = (UserDto)session.getAttribute("loginUser");
        int invoice = rentService.createInvoice(serviceId,loginUser.getType(),rentalRate,overMileageFee);
        return invoice+"";
    }

    @RequestMapping("/getInvoice")
    public Map<String ,Object> getInvoice(HttpServletRequest request){
        HttpSession session = request.getSession();
        UserDto loginUser = (UserDto)session.getAttribute("loginUser");
        List<InvoiceDto> invoiceDtos =
                rentService.getInvoice(loginUser.getCustomerId(),loginUser.getType());
        Map<String, Object> res = new HashMap<>();
        res.put("data", invoiceDtos);
        res.put("code", 0);
        res.put("msg", "");
        return res;
    }


    @RequestMapping("/getPay")
    public String getPay(String invoiceId,String invoiceAmount,String cardType,String cardNum){
        String paymsg = rentService.getPay(invoiceId,invoiceAmount,cardType,cardNum);
        return paymsg;
    }

    @RequestMapping("/getPayDetail")
    public PaymentDto getPayDetail(String invoiceId){
        PaymentDto paymentDto = rentService.getPayDetail(invoiceId);
        return paymentDto;
    }


}

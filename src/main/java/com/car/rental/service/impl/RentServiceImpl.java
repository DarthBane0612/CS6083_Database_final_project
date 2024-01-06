package com.car.rental.service.impl;

import com.car.rental.dto.*;
import com.car.rental.mapper.*;
import com.car.rental.pojo.*;
import com.car.rental.service.RentService;
import com.car.rental.utils.BeanCopyUtils;
import com.car.rental.utils.GetIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RentServiceImpl implements RentService {
    @Autowired
    private OfficeMapper officeMapper;

    @Autowired
    private VehiclesMapper vehiclesMapper;

    @Autowired
    private RentalServiceMapper rentalServiceMapper;

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private IndividualCouponMapper individualCouponMapper;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private CorporateMapper corporateMapper;

    @Autowired
    private CorprateCouponMapper corprateCouponMapper;

    @Override
    public List<OfficeDto> getOffice() {
        List<OfficePojo> officePojos= officeMapper.getOffice();
        List<OfficeDto> officeDtos = new ArrayList<>();
        if(officePojos.size()>0){
            for (OfficePojo office: officePojos) {
                OfficeDto officeDto = new OfficeDto();
                officeDto.setCity(office.getCity());
                officeDto.setId(office.getOfficeId());
                officeDto.setName(office.getLocName());
                officeDto.setState(office.getState());
                officeDto.setPhone(office.getPhone());
                officeDto.setZipCode(office.getZipCode());
                officeDto.setStreet(office.getStreet());
                officeDtos.add(officeDto);
            }
        }
        return officeDtos;
    }


    @Override
    public List<VehiclesDto> getVehicles(String officeId) {
        List<VehiclesPojo> VehiclesList = vehiclesMapper.getVehicles(officeId);
        List<VehiclesDto> carDtos = BeanCopyUtils.copyBeanList(VehiclesList, VehiclesDto.class);
        return carDtos;
    }

    @Override
    @Transactional
    public int createService(String customerId, String vin, String serviceId,String sOdometer,String type) {
        String couponId = GetIdUtils.verificationCode();
        sOdometer = vehiclesMapper.selectsOdometerByVin(vin);
        rentalServiceMapper.createService(customerId,vin,serviceId,sOdometer);
        String individualDiscount ="0.32";
        couponMapper.createCoupon(couponId,type,serviceId);
        if(type.equals("2")){
            return individualCouponMapper.individualCoupon(couponId,individualDiscount);
        }
        String corprateDiscount ="0.32";
        if(type.equals("1")){
            return corprateCouponMapper.individualCoupon(couponId,corprateDiscount);
        }
        return 0;
    }

    @Override
    public List<RentalServiceDto> getService(String customerId) {
        List<RentalServicePojo> rentalServicePojos=  rentalServiceMapper.getService(customerId);
        List<RentalServiceDto> rentalServiceDtos = BeanCopyUtils.copyBeanList(rentalServicePojos, RentalServiceDto.class);
        return rentalServiceDtos;
    }

    @Override
    @Transactional
    public int createInvoice(String serviceId,String type,String rentalRate,String overMileageFee) {
        RentalServicePojo rentalServicePojo =
                rentalServiceMapper.selectSodometerByserviceId(serviceId);
        IndividualCouponPojo individualCouponPojo = null ;
        String invoceId = GetIdUtils.verificationCode();
        String eOdometer = GetIdUtils.verificationCode2();
        rentalServiceMapper.updateEodometerByserviceId(serviceId,eOdometer);
        double odometer =
                Double.valueOf(eOdometer).doubleValue()-
                        Double.valueOf(rentalServicePojo.getSOdometer()).doubleValue();
        double invoiceAmount;
        double limit = Double.valueOf(rentalServicePojo.getLimit()).doubleValue();
        double rentalRate1 =  Double.valueOf(rentalRate).doubleValue();
        double overMileageFee1 = Double.valueOf(overMileageFee).doubleValue();
        if(odometer>limit){
            invoiceAmount =((odometer-limit)*overMileageFee1+limit*rentalRate1);
        }else{
            invoiceAmount = odometer*rentalRate1;
        }
        if(type.equals("2")){
            individualCouponPojo = individualCouponMapper.selectDiscount(serviceId,type);
            if(!(individualCouponPojo.getDiscount()).equals("")&& individualCouponPojo.getDiscount()!=null){
                String invoiceAmout1 = "";
                invoiceAmout1 = invoiceAmount*Double.valueOf(individualCouponPojo.getDiscount()).doubleValue()+"";
                return invoiceMapper.insertInvoice(invoceId,serviceId,invoiceAmout1);
            }else {
                return invoiceMapper.insertInvoice(invoceId,serviceId,invoiceAmount+"");
            }
        }
        CorprateCouponPojo corporatePojo = null;
        if(type.equals("1")){
            corporatePojo = corprateCouponMapper.selectDiscount(serviceId,type);
            if(!(corporatePojo.getDiscount()).equals("")&& corporatePojo.getDiscount()!=null){
                String invoiceAmount1 = invoiceAmount*Double.valueOf(corporatePojo.getDiscount()).doubleValue()+"";
                return invoiceMapper.insertInvoice(invoceId,serviceId,invoiceAmount1);
            }else{
                return invoiceMapper.insertInvoice(invoceId,serviceId,invoiceAmount+"");
            }
        }
        return 0;
    }

    @Override
    public List<InvoiceDto> getInvoice(String customerId, String type) {
        List<InvoicePojo> invoicePojos = invoiceMapper.selectInvoiceByCustomerId(customerId);
        List<InvoiceDto> InvoiceDtos = BeanCopyUtils.copyBeanList(invoicePojos, InvoiceDto.class);
        return InvoiceDtos;
    }

    @Override
    public String getPay(String invoiceId, String invoiceAmount, String cardType, String cardNum) {
        PaymentPojo paymentPojo = paymentMapper.selectPaymentByInvoiced(invoiceId);
        if(paymentPojo!=null){
            return "0";
        }
        String paymentId = GetIdUtils.verificationCode();

        int num = paymentMapper.insertPayment(paymentId, cardType, cardNum, invoiceId);
        return num+"";
    }

    @Override
    public PaymentDto getPayDetail(String invoiceId) {
        PaymentPojo paymentPojo = paymentMapper.selectPaymentByInvoiced(invoiceId);
        PaymentDto paymentDto = null;
        if(paymentPojo!=null){
            paymentDto = BeanCopyUtils.copyBean(paymentPojo, PaymentDto.class);
            paymentDto.setPaymentMsg(paymentDto.getPaymentMethod()=="2"?"debit_card":"credit_card");
        }
        return paymentDto;
    }
}

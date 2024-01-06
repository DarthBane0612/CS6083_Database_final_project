package com.car.rental.utils;


import java.util.Random;

public class GetIdUtils {

    public static String verificationCode() {
        Random random = new Random();
        String verificationCode = String.valueOf(random.nextInt(9) + 1);
        for (int i = 0; i < 5; i++) {
            verificationCode += random.nextInt(10);
        }
        return verificationCode;
    }
    public static String verificationCode2() {
        Random random = new Random();
        String verificationCode = String.valueOf(random.nextInt(9) + 1);
        for (int i = 0; i < 2; i++) {
            verificationCode += random.nextInt(10);
        }
        return verificationCode;
    }
}

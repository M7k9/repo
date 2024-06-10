package com.m7k.otp;

import com.m7k.otp.Service.EmailService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class MockedEmail implements EmailService {


    @Override
    public void sendOtpEmail(String to, String otp) {
   
        System.out.println("Sending OTP to " + to + ": " + otp); 

    }


}

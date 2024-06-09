package com.m7k.otp;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.m7k.otp.Service.EmailService;

@Service
@Primary
public class MockedEmailservice implements EmailService {
    
    @Override
    public void SendOtpEmail(String to, String otp) {
        System.out.println("Mocked email service: Sending OTP to " + to + " with OTP: " + otp);
    }
}

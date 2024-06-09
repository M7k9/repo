package com.m7k.otp.Service;


import org.springframework.stereotype.Service;

@Service
public interface EmailService {

 

   
    public void SendOtpEmail(String to, String otp);

}


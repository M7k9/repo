package com.m7k.otp.Service;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class EmailService {

    private final JavaMailSender javaMailSender;

    
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
  
    }

    public void sendOtpEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to);
        message.setSubject("OTP for your account");
        message.setText("Your OTP is: " + otp);
        javaMailSender.send(message);
    }

   
    public String mockSendOtpEmail(String to, String otp) {
   
        System.out.println("Sending OTP to " + to + ": " + otp); 
        return "";
    }

}


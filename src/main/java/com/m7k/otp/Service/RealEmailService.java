package com.m7k.otp.Service;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class RealEmailService implements EmailService {

    private final JavaMailSender javaMailSender;

    
    public RealEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
  
    }
    @Override
    public void sendOtpEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to);
        message.setSubject("OTP for your account");
        message.setText("Your OTP is: " + otp);
        javaMailSender.send(message);
    }

   


}


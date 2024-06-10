package com.m7k.otp.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class OtpService {
    
    private static final Random random = new SecureRandom();
    private final Map<Long, String> otpCache = new HashMap<>();


    public String generateOtp(Long id){
        String otp = String.format("%06d", random.nextInt(1000000+ 10000));
        otpCache.put(id, otp);
        return otp;
    }

    public Boolean validateOtp(Long id, String otp){
        String cachedOtp = otpCache.get(id);
        return cachedOtp != null && cachedOtp.equals(otp);
    }
}

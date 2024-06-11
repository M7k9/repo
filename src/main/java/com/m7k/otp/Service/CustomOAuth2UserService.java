package com.m7k.otp.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.m7k.otp.Entity.MyUser;
import com.m7k.otp.Service.EmailService;
import com.m7k.otp.Service.OtpService;
import com.m7k.otp.Service.UserService;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private static final Logger logger = LoggerFactory.getLogger(CustomOAuth2UserService.class);

    private final UserService userService;
    private final OtpService otpService;
    private final EmailService emailService;

    public CustomOAuth2UserService(UserService userService, OtpService otpService, EmailService emailService) {
        this.userService = userService;
        this.otpService = otpService;
        this.emailService = emailService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        if (email == null || email.isEmpty()) {
            logger.error("Email not found from OAuth2 provider");
            throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
        }

        logger.info("OAuth2 login successful. Email: {}", email);

        Optional<MyUser> existingUser = userService.findByEmail(email);

        MyUser user;
        if (existingUser.isPresent()) {
            user = existingUser.get();
            logger.info("User already exists: {}", user);
        } else {
            user = new MyUser();
            user.setEmail(email);
            userService.createUser(user);
            logger.info("New user created: {}", user);
        }

        try {
            String otp = otpService.generateOtp(user.getId());
            emailService.SendOtpEmail(user.getEmail(), otp);
            logger.info("OTP generated and email sent to: {}", user.getEmail());
        } catch (Exception e) {
            logger.error("Error generating OTP or sending email", e);
            throw new OAuth2AuthenticationException(null, "Error generating OTP or sending email", e);
        }

        return oAuth2User;
    }
}

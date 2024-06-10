package com.m7k.otp.Controller;

import com.m7k.otp.Entity.MyUser;
import com.m7k.otp.Service.EmailService;
import com.m7k.otp.Service.OtpService;
import com.m7k.otp.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class ControllerTwo {
    private final UserService userService;
    private final OtpService otpService;
    private final EmailService emailService;

    public ControllerTwo(UserService userService, OtpService otpService, EmailService emailService) {
        this.userService = userService;
        this.otpService = otpService;
        this.emailService = emailService;
    }
    @PostMapping("/register")
    public String register(@RequestBody MyUser user) {
        MyUser savedUser = userService.createUser(user);
        String otp = otpService.generateOtp(user.getId());
        emailService.sendOtpEmail(savedUser.getEmail(), otp);

        return "";
    }
    @PostMapping("/validate")
    public String validate(@RequestParam Long id, @RequestBody String otp) {
        Boolean isvalid = otpService.validateOtp(id,otp);
        if (isvalid) {
            return "valid";
        }else {
            return "invalid";
        }
    }

}

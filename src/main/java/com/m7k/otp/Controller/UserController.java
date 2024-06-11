package com.m7k.otp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.m7k.otp.Entity.MyUser;
import com.m7k.otp.Service.EmailService;
import com.m7k.otp.Service.OtpService;
import com.m7k.otp.Service.UserService;

@Controller
@RequestMapping("api/auth")
public class UserController {

    private final UserService userService;
    private final OtpService otpService;
    private final EmailService emailService;

    public UserController(UserService userService, OtpService otpService, EmailService emailService) {
        this.userService = userService;
        this.otpService = otpService;
        this.emailService = emailService;
    }

    @GetMapping("/login")
    public String showForm(Model model) {
        model.addAttribute("user", new MyUser());
        return "login";
    }

    @PostMapping("/login")
    public String submitForm(@ModelAttribute MyUser myUser) {
        MyUser savedUser = userService.createUser(myUser);
        String otp = otpService.generateOtp(savedUser.getId());
        emailService.SendOtpEmail(myUser.getEmail(), otp);

        return "redirect:/api/auth/validate?id=" + savedUser.getId();
    }

    @GetMapping("/validate")
    public String showOtpValidationForm(@RequestParam Long id, Model model) {
        model.addAttribute("id", id);
        return "validate";
    }

    @PostMapping("/validate")
    public String validateOtp(@RequestParam Long id, @RequestParam String otp, Model model) {
        boolean isValid = otpService.validateOtp(id, otp);
        if (isValid) {
            model.addAttribute("message", "OTP is valid.");
            return "success";
        } else {
            model.addAttribute("error", "Invalid OTP.");
            model.addAttribute("id", id);
            return "validate";
        }

    }

}

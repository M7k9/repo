package com.m7k.otp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.m7k.otp.Controller.UserController;
import com.m7k.otp.Entity.MyUser;
import com.m7k.otp.Service.EmailService;

@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class OtpApplicationTests {


    @Autowired
    private EmailService MockedEmailservice;

    @Autowired
    UserController userController;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:latest"
    );

    @Test
    void testCreateUserAndSendOtp() throws Exception {


       userController.submitForm(new MyUser("hello@gmai.com", "password"));

         
    }

}

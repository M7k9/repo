package com.m7k.otp;

import com.m7k.otp.Controller.UserController;
import com.m7k.otp.Entity.MyUser;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.security.Key;
import java.util.Base64;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OtpApplicationTests {

    @Autowired
    private UserController userController;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:latest"
    );


    @Test
    void testCreateUserAndSendOtp() throws Exception {
        // JSON request body for user creation
//        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
//        formData.add("email", "test@example.com");
//        formData.add("password", "password");

        userController.submitForm(new MyUser(
                "asdfa@gmail.com", "asdfasdf"
        ));
    }

        public static void main(String[] args) {
            Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
            System.out.println("Key: "+base64Key);

        }

}

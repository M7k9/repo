package com.m7k.otp;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.m7k.otp.Service.EmailService;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OtpApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService emailService;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:latest"
    );

    @Test
    void testCreateUserAndSendOtp() throws Exception {
        //  request body for user creation
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("email", "test@example.com");
        formData.add("password", "password");

        // Perform the POST request to register a user
        mockMvc.perform(post("/api/user/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .params(formData))
                .andExpect(status().is3xxRedirection());

        // Verify that an email was "sent"
        verify(emailService, times(1)).sendOtpEmail(eq("test@example.com"), anyString());
    }

}

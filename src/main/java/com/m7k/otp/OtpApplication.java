package com.m7k.otp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.m7k.otp.Config.RsaKeyProperties;


@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)

public class OtpApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtpApplication.class, args);
	}

}

package com.edu.mse.pwc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableWebSecurity
@EnableResourceServer
@EnableAuthorizationServer
public class PwcApplication {

    //Used: //https://www.baeldung.com/role-and-privilege-for-spring-security-registration


    public static void main(String[] args) {
        SpringApplication.run(PwcApplication.class, args);
    }

}

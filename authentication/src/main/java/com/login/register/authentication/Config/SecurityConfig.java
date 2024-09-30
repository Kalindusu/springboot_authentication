package com.login.register.authentication.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration  // Marks this class as a configuration class
public class SecurityConfig {

    @Bean  // This registers the PasswordEncoder as a bean in the Spring context
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // BCrypt is a secure and commonly used password encoder
    }
}

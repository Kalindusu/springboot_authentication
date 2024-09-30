package com.login.register.authentication.controller;

import com.login.register.authentication.dto.EmployeeDTO;
import com.login.register.authentication.dto.LoginDTO;
import com.login.register.authentication.payloadresponse.LoginMessage;
import com.login.register.authentication.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin
public class EmployeeController {

    private final EmployeeService employeeService;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);  // Add logger

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(path = "/save")
    public String saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        logger.info("Request received to save employee: {}", employeeDTO.getEmail());  // Log request
        String id = employeeService.addEmployee(employeeDTO);
        logger.info("Employee saved successfully with ID: {}", id);  // Log success
        return id;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO) {
        logger.info("Login attempt for email: {}", loginDTO.getEmail());  // Log login attempt
        try {
            LoginMessage loginMessage = employeeService.loginEmployee(loginDTO);
            if (loginMessage == null || !loginMessage.getStatus()) {  // Use getStatus() instead of isSuccess()
                logger.warn("Login failed for email: {}", loginDTO.getEmail());  // Log failure
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
            logger.info("Login successful for email: {}", loginDTO.getEmail());  // Log success
            return ResponseEntity.ok(loginMessage);
        } catch (Exception e) {
            logger.error("Error occurred during login for email: {}", loginDTO.getEmail(), e);  // Log exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }
}

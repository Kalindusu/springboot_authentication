package com.login.register.authentication.service.impl;

import com.login.register.authentication.dto.EmployeeDTO;
import com.login.register.authentication.dto.LoginDTO;
import com.login.register.authentication.entity.Employee;
import com.login.register.authentication.payloadresponse.LoginMessage;
import com.login.register.authentication.repo.EmployeeRepo;
import com.login.register.authentication.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmployeeImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeImpl.class);  // Add logger

    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {
        logger.info("Adding new employee: {}", employeeDTO.getEmail());  // Log employee addition
        Employee employee = new Employee(
                employeeDTO.getEmployeeid(),
                employeeDTO.getEmployeename(),
                employeeDTO.getEmail(),
                this.passwordEncoder.encode(employeeDTO.getPassword())
        );
        employeeRepo.save(employee);
        logger.info("Employee saved successfully: {}", employeeDTO.getEmployeename());  // Log success
        return employee.getEmployeename();
    }

    @Override
    public LoginMessage loginEmployee(LoginDTO loginDTO) {
        logger.info("Attempting to login employee with email: {}", loginDTO.getEmail());  // Log login attempt

        // Find employee by email
        Employee employee = employeeRepo.findByEmail(loginDTO.getEmail());

        if (employee != null) {
            logger.info("Employee found with email: {}", loginDTO.getEmail());  // Log found employee

            String rawPassword = loginDTO.getPassword();
            String encodedPassword = employee.getPassword();

            // Check if the password matches
            boolean isPasswordCorrect = passwordEncoder.matches(rawPassword, encodedPassword);

            if (isPasswordCorrect) {
                logger.info("Login successful for email: {}", loginDTO.getEmail());  // Log successful login
                return new LoginMessage("Login Success", true);
            } else {
                logger.warn("Login failed: incorrect password for email: {}", loginDTO.getEmail());  // Log password mismatch
                return new LoginMessage("Password does not match", false);
            }
        } else {
            logger.warn("Login failed: email not found for email: {}", loginDTO.getEmail());  // Log email not found
            return new LoginMessage("Email does not exist", false);
        }
    }
}

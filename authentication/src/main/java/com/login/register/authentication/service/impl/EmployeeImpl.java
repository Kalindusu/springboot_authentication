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
        // Encode password before saving it
        String encodedPassword = this.passwordEncoder.encode(employeeDTO.getPassword());
        logger.info("Encoded password for employee {}: {}", employeeDTO.getEmail(), encodedPassword);  // Log encoded password

        Employee employee = new Employee(
                employeeDTO.getEmployeeid(),
                employeeDTO.getEmployeename(),
                employeeDTO.getEmail(),
                encodedPassword  // Store the encoded password
        );
        employeeRepo.save(employee);
        logger.info("Employee saved successfully: {}", employeeDTO.getEmployeename());
        return employee.getEmployeename();
    }

    @Override
    public LoginMessage loginEmployee(LoginDTO loginDTO) {
        try {
            logger.info("Attempting to login employee with email: {}", loginDTO.getEmail());

            // Find employee by email
            Employee employee = employeeRepo.findByEmail(loginDTO.getEmail());

            if (employee == null) {
                logger.warn("No employee found with email: {}", loginDTO.getEmail());
                return new LoginMessage("Email does not exist", false);
            }

            // Get the raw password entered by the user and the encoded password from the database
            String rawPassword = loginDTO.getPassword();
            String encodedPassword = employee.getPassword();

            logger.info("Raw password: {}", rawPassword);  // Log raw password (for debugging only)
            logger.info("Encoded password in DB: {}", encodedPassword);  // Log encoded password

            // Check if the password matches
            boolean isPasswordCorrect = passwordEncoder.matches(rawPassword, encodedPassword);

            if (isPasswordCorrect) {
                logger.info("Login successful for email: {}", loginDTO.getEmail());
                return new LoginMessage("Login Success", true);
            } else {
                logger.warn("Incorrect password for email: {}", loginDTO.getEmail());
                return new LoginMessage("Password does not match", false);
            }
        } catch (Exception e) {
            logger.error("Error occurred during login for email: {}", loginDTO.getEmail(), e);
            return new LoginMessage("An internal error occurred", false);
        }
    }
}

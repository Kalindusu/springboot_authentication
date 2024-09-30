package com.login.register.authentication.service.impl;

import com.login.register.authentication.dto.EmployeeDTO;
import com.login.register.authentication.entity.Employee;
import com.login.register.authentication.repo.EmployeeRepo;
import com.login.register.authentication.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;  // Use Spring Security PasswordEncoder
import org.springframework.stereotype.Service;

@Service  // Register this class as a service in Spring's context
public class EmployeeImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Use Spring Security's PasswordEncoder

    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {
        // Creating an Employee entity from the DTO
        Employee employee = new Employee(
                employeeDTO.getEmployeeid(),
                employeeDTO.getEmployeename(),
                employeeDTO.getEmail(),
                passwordEncoder.encode(employeeDTO.getPassword())  // Encode the password
        );

        // Save the employee to the repository (assume employeeRepo is a JpaRepository)
        employeeRepo.save(employee);

        // Return the employee name after saving
        return employee.getEmployeename();
    }
}

package com.login.register.authentication.service;

import com.login.register.authentication.dto.EmployeeDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
@Service
public interface EmployeeService {

    String addEmployee(EmployeeDTO employeeDTO);
}

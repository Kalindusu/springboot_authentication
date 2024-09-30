package com.login.register.authentication.service.impl;

import com.login.register.authentication.dto.EmployeeDTO;
import com.login.register.authentication.entity.Employee;
import com.login.register.authentication.repo.EmployeeRepo;
import com.login.register.authentication.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeIMPL implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private  passwordEncoder passwordEncoder;
    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(
                employeeDTO.getEmployeeid(),
                employeeDTO.getEmployeename(),
                employeeDTO.getEmail(),

                this.passwordEncoder.encode(employeeDTO.getPassword())

        );
        employeeRepo.save(employee);
        return  employee.getEmployeename();

    }

    private class passwordEncoder {
        public String encode(String password) {
            return password;
        }
    }
}

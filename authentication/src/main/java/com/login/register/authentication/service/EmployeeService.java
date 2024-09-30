package com.login.register.authentication.service;

import com.login.register.authentication.dto.EmployeeDTO;
import com.login.register.authentication.dto.LoginDTO;
import com.login.register.authentication.payloadresponse.LoginMessage;

public interface EmployeeService {
    String addEmployee(EmployeeDTO employeeDTO);

    LoginMessage loginEmployee(LoginDTO loginDTO);
}

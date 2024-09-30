package com.login.register.authentication.controller;

import com.login.register.authentication.dto.EmployeeDTO;
import com.login.register.authentication.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class EmployeeController {

    @Autowired
    public EmployeeService employeeService;

    @PostMapping("/save")
    public String saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        String id=employeeService.addEmployee(employeeDTO);
        return  id;
    }


}

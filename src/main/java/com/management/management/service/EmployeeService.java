package com.management.management.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.management.entity.Employee;
import com.management.management.exception.ResourceNotFoundException;
import com.management.management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService  {
    @Autowired
    private EmployeeRepository employeeRepository;

       public String getEmployeeData() throws JsonProcessingException {
        ObjectMapper objectMapper1 = new ObjectMapper();
        List<Employee> emp1 = employeeRepository.findAll();
        String employeesResponseString =objectMapper1.writeValueAsString(emp1);
        System.out.println(employeesResponseString);
        return employeesResponseString;

    }
    public String getEmployeeDataById(int id) throws JsonProcessingException {

        ObjectMapper objectMapper1 = new ObjectMapper();
        Optional<Employee> emp = employeeRepository.findById(id);
        String employeeResponseString;
        if (emp.isPresent()) {
            employeeResponseString = objectMapper1.writeValueAsString(emp.get());
            System.out.println(employeeResponseString);
        } else {
            throw new ResourceNotFoundException("No Record Found");
        }
        return employeeResponseString;
    }


}

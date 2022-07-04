package com.management.management.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.management.management.exception.ResourceNotFoundException;
import com.management.management.service.EmployeeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all-employees")
    public ResponseEntity<String> getData() throws JsonProcessingException {
        ResponseEntity<String> response1;
        try {
            String empsResponseString = employeeService.getEmployeeData();
            response1 = ResponseEntity
                    .status(HttpStatus.OK).body(empsResponseString);
            return response1;
        } catch (ResourceNotFoundException r) {
            response1 = ResponseEntity.status(HttpStatus.OK).body("Not found");
            return response1;
        } catch (Exception e) {
            response1 = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception Occured");
            return response1;
        }
    }


    @GetMapping("employee/{id}")
    public ResponseEntity<String> getDataById(@PathVariable int id) throws JsonProcessingException {
        ResponseEntity<String> response;
        try {
            String empResponseString = employeeService.getEmployeeDataById(id);
            response = ResponseEntity.status(HttpStatus.OK).body(empResponseString);
            return response;
        } catch (ResourceNotFoundException r) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found");
            return response;
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception Occurred");
            return response;
        }

    }
}
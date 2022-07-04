package com.management.management.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.management.management.exception.ResourceNotFoundException;
import com.management.management.service.CompanyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyServices companyServices;

//    @GetMapping("/all-employees")
//    public List<Employee> getData(){
//        return employeeService.getEmployeeData();
//    }
//    @GetMapping("employee/{id}")
//    public Employee getDataById(@PathVariable int id){
//        return employeeService.getEmployeeDataById(id);
//    }

    @GetMapping("/details")
    public ResponseEntity<String> getData() throws JsonProcessingException {
        ResponseEntity<String>response1;

        try {
            String compsResponseString = companyServices.getCompData();
            response1 =ResponseEntity.status(HttpStatus.OK).body(compsResponseString);
            return response1;
        } catch (ResourceNotFoundException r) {
            response1 = ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Entity not found");
            return response1;
        } catch (Exception e) {
            response1 = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception Occurred");
            return response1;
        }
    }

    @GetMapping("company/{compId}")
    public ResponseEntity<String> getCompDataById(@PathVariable int compId) throws JsonProcessingException {
        ResponseEntity<String> response;
        try {
            String compResponseString = companyServices.getCompanyDataById(compId);
            response =ResponseEntity.status(HttpStatus.OK).body(compResponseString);
            return response;
        } catch (ResourceNotFoundException r) {
            response = ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Entity not found");
            return response;
        } catch (Exception e) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception Occurred");
            return response;
        }
    }
}
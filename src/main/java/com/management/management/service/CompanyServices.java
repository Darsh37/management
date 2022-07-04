package com.management.management.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.management.entity.Company;
import com.management.management.exception.ResourceNotFoundException;
import com.management.management.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CompanyServices {
    @Autowired
    private CompanyRepository companyRepository;

    public String getCompData() throws JsonProcessingException {
        try {
            ObjectMapper objectMapper1 = new ObjectMapper();
            List<Company> comp = companyRepository.findAll();
            String companiesResponseString = objectMapper1.writeValueAsString(comp);
            System.out.println(companiesResponseString);
            return companiesResponseString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getCompanyDataById(int compId) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Optional<Company> comp = companyRepository.findById(compId);
        String compResponseString;
        if (comp.isPresent()) {
            compResponseString = objectMapper.writeValueAsString(comp.get());
            System.out.println(compResponseString);
        } else {
            throw new ResourceNotFoundException("No Record Found");
        }
        return compResponseString;
    }


}

package com.umerscode.Jobboard.Controller;

import com.umerscode.Jobboard.Entity.Company;
import com.umerscode.Jobboard.Service.CompanyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    @Autowired
    private final CompanyServiceImpl companyService;

    @GetMapping("/all")
    public ResponseEntity<List<Company>> getListOfCompany(){
        return new ResponseEntity<>(companyService.getCompanies(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Company> createCompany(@RequestBody Company company){
        return new ResponseEntity<>(companyService.createCompany(company),HttpStatus.CREATED);
    }

    @PutMapping ("/update")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company){
        return new ResponseEntity<>(companyService.updateCompany(company), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCompanyById(@PathVariable("id") int CompanyId){
        companyService.deleteCompany(CompanyId);
    }
}

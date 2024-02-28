package com.umerscode.Jobboard.Service;

import com.umerscode.Jobboard.Entity.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanies();
    Company createCompany(Company newCompany);
    Company updateCompany(Company updatedCompany);
    void deleteCompany(int id);
}

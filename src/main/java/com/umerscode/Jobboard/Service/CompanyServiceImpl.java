package com.umerscode.Jobboard.Service;

import com.umerscode.Jobboard.Entity.Company;
import com.umerscode.Jobboard.Repository.CompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private final CompanyRepo companyRepo;

    @Override
    public List<Company> getCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public Company createCompany(Company newCompany) {
        return companyRepo.save(newCompany);
    }

    @Transactional
    @Override
    public Company updateCompany(Company updatedCompany) {
        Company existCompany = companyRepo.findById(updatedCompany.getId()).get();

        existCompany.setName(updatedCompany.getName());
        existCompany.setEmail(updatedCompany.getEmail());
        existCompany.setAddress(updatedCompany.getAddress());
        existCompany.setPhone(updatedCompany.getPhone());

        return existCompany;
    }

    @Override
    public void deleteCompany(int id) {

        companyRepo.deleteById((long) id);
    }
}

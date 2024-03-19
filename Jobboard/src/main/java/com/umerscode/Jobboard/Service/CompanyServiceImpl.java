package com.umerscode.Jobboard.Service;

import com.umerscode.Jobboard.Dto.RegisterCompanyDto;
import com.umerscode.Jobboard.Entity.AppUser;
import com.umerscode.Jobboard.Entity.Company;
import com.umerscode.Jobboard.Entity.Role;
import com.umerscode.Jobboard.Repository.AppUserRepo;
import com.umerscode.Jobboard.Repository.CompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepo companyRepo;
    private final AppUserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

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

    @Override
    public Company registerCompany(RegisterCompanyDto registerDto) {
        if(userRepo.findUserByEmail(registerDto.getUser().getEmail()).isPresent())
            throw new IllegalStateException("User email is taken");

        AppUser user = registerDto.getUser();
        user.setPassword(passwordEncoder.encode(registerDto.getUser().getPassword()));
        user.setRole(Role.EMPLOYER);
        userRepo.save(user);

        Company company = registerDto.getCompany();
        return companyRepo.save(company);
    }
}

package com.umerscode.Jobboard.Repository;

import com.umerscode.Jobboard.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Long> {

    Company findByEmail(String email);

    Optional<Company> findByName(String companyName);


}

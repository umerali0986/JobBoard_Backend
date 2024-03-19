package com.umerscode.Jobboard.Repository;

import com.umerscode.Jobboard.Entity.Company;
import com.umerscode.Jobboard.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepo extends JpaRepository<Job, Long> {

    Optional<List<Job>> findByJobType(String jobType);
    Optional<List<Job>> findByCompanyEmail(String email);
    //Optional<Company> findCompanyById(int jobId);


}

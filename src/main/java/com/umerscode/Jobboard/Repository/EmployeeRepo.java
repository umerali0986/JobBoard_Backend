package com.umerscode.Jobboard.Repository;

import com.umerscode.Jobboard.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    Employee findByEmail(String email);

    Optional<List<Employee>> findByJobType(String jobType);
}

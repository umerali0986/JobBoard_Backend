package com.umerscode.Jobboard.Service;

import com.umerscode.Jobboard.Dto.RegisterEmployeeDto;
import com.umerscode.Jobboard.Entity.AppUser;
import com.umerscode.Jobboard.Entity.Employee;
import com.umerscode.Jobboard.Entity.Role;
import com.umerscode.Jobboard.Repository.AppUserRepo;
import com.umerscode.Jobboard.Repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepo userRepo;
    private final EmployeeRepo employeeRepo;

    @Override
    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public List<Employee> getEmployeeByJobType(String jobType) {

        return employeeRepo.findByJobType(jobType).get();
    }

//    @Override
//    public Employee createEmployee(Employee newEmployee) {
//        newEmployee.setEmployeeCode(UUID.randomUUID().toString().substring(0,8));
//        return employeeRepo.save(newEmployee);
//    }

    @Transactional
    @Override
    public Employee updateEmployee(Employee employee) {

        Employee existEmployee = employeeRepo.findById(employee.getId()).get();

        existEmployee.setFirstName(employee.getFirstName());
        existEmployee.setLastName(employee.getLastName());
        existEmployee.setAddress(employee.getAddress());
        existEmployee.setEducationLevel(employee.getEducationLevel());
        existEmployee.setEmail(employee.getEmail());
        existEmployee.setJobType(employee.getJobType());
        existEmployee.setPhone(employee.getPhone());
        existEmployee.setYearsOfExperience(employee.getYearsOfExperience());

        return existEmployee;
    }

    @Override
    public Employee registerEmployee(RegisterEmployeeDto registerDto) {

        String uuid = UUID.randomUUID().toString().substring(0,8);
//        while(employeeRepo.findByEmployeeCode(uuid).isPresent())
//        { uuid = UUID.randomUUID().toString().substring(0,8);}

        if(userRepo.findUserByEmail(registerDto.getUser().getEmail()).isPresent())
            throw new IllegalStateException("User email is taken");

        AppUser user = registerDto.getUser();
        user.setPassword(passwordEncoder.encode(registerDto.getUser().getPassword()));
        user.setRole(Role.EMPLOYEE);
        userRepo.save(user);

        Employee employee = registerDto.getEmployee();
        employee.setEmployeeCode(uuid);
        return employeeRepo.save(employee);
    }

    @Override
    public void deleteEmployeeById(int id) {

        employeeRepo.deleteById((long) id);
    }
}

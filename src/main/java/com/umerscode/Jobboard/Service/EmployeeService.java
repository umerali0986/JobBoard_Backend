package com.umerscode.Jobboard.Service;

import com.umerscode.Jobboard.Dto.RegisterEmployeeDto;
import com.umerscode.Jobboard.Entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();
    List<Employee> getEmployeeByJobType(String jobType);
//    Employee createEmployee(Employee newEmployee);
    Employee updateEmployee(Employee employee);
    void deleteEmployeeById(int id);

    Employee registerEmployee(RegisterEmployeeDto registerDto);

}

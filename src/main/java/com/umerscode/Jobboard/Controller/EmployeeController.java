package com.umerscode.Jobboard.Controller;

import com.umerscode.Jobboard.Dto.RegisterEmployeeDto;
import com.umerscode.Jobboard.Entity.Employee;
import com.umerscode.Jobboard.Jwt.JwtService;
import com.umerscode.Jobboard.Repository.EmployeeRepo;
import com.umerscode.Jobboard.Service.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = "/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepo employeeRepo;
    private final EmployeeServiceImpl employeeService;
    private final JwtService jwtServices;

    @GetMapping(path = "/all")
    public ResponseEntity<List<Employee>> getEmployees(){
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Employee>> getEmployeeByJobType(@RequestParam("job_type") String jobType){
        return  new ResponseEntity<>(employeeService.getEmployeeByJobType(jobType),HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<Employee> registerEmployee(@RequestBody RegisterEmployeeDto registerDto){
        System.out.println("register...");
        Employee registerEmployee = employeeService.registerEmployee(registerDto);
        final String jwtToken = jwtServices.generateJwt(registerDto.getUser());
        return new ResponseEntity<>(registerEmployee, CREATED);
    }
//    @PostMapping("/create")
//    public ResponseEntity<Employee> createEmployee(@RequestBody Employee newEmployee){
//        return  new ResponseEntity<>(service.createEmployee(newEmployee),HttpStatus.CREATED);
//    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee updatedEmployee){
        return  new ResponseEntity<>(employeeService.updateEmployee(updatedEmployee),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public void deleteEmployeeById(@PathVariable int id){
       employeeService.deleteEmployeeById(id);
    }

}

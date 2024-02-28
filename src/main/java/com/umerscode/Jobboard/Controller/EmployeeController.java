package com.umerscode.Jobboard.Controller;

import com.umerscode.Jobboard.Entity.Employee;
import com.umerscode.Jobboard.Service.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    private final EmployeeServiceImpl service;

    @GetMapping(path = "/all")
    public ResponseEntity<List<Employee>> getEmployees(){
        return new ResponseEntity<>(service.getEmployees(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Employee>> getEmployeeByJobType(@RequestParam("job_type") String jobType){
        return  new ResponseEntity<>(service.getEmployeeByJobType(jobType),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee newEmployee){
        return  new ResponseEntity<>(service.createEmployee(newEmployee),HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee updatedEmployee){
        return  new ResponseEntity<>(service.updateEmployee(updatedEmployee),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable int id){
       service.deleteEmployeeById(id);
    }

}

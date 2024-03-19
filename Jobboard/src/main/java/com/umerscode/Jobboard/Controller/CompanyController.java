package com.umerscode.Jobboard.Controller;

import com.umerscode.Jobboard.Dto.RegisterCompanyDto;
import com.umerscode.Jobboard.Entity.Company;
import com.umerscode.Jobboard.Entity.Employee;
import com.umerscode.Jobboard.Entity.Job;
import com.umerscode.Jobboard.Jwt.JwtService;
import com.umerscode.Jobboard.Repository.CompanyRepo;
import com.umerscode.Jobboard.Service.CompanyServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final JwtService jwtServices;
    private final CompanyServiceImpl companyService;
    private final CompanyRepo companyRepo;


//    @GetMapping("/findEmployeeByJob/{jobType}")
//    @PreAuthorize("hasAuthority('EMPLOYER')")
//    public List<Employee> getEmployeeByJob(@PathVariable("jobType") String jobType){
//        return companyService.getEmployeeByJobType(jobType);
//    }
//
//    @GetMapping("/jobs")
//    @PreAuthorize("hasAuthority('EMPLOYER')")
//    public ResponseEntity<List<Job>> getAllJobs(){
//        return ResponseEntity.ok().body(companyService.getAllJobs());
//    }
//
//    @DeleteMapping("/job/{id}")
//    @PreAuthorize("hasAuthority('EMPLOYER')")
//    public void deleteJobById(@PathVariable("id") Long id){
//        companyService.deleteJobById(id);
//    }
//
//    @PutMapping("/job/update/{id}")
//    @PreAuthorize("hasAuthority('EMPLOYER')")
//    public ResponseEntity<Job> updateJob(@PathVariable("id") Long id,
//                                         @RequestBody Job job){
//        return ResponseEntity.ok().body(companyService.updateJob(id,job));
//    }
//
//    @PostMapping("/create/job")
//    @PreAuthorize("hasAuthority('EMPLOYER')")
//    public ResponseEntity<Job> createNewJob(@RequestBody Job job){
//        return new ResponseEntity<>(companyService.createNewJob(job), CREATED);
//    }

//    @GetMapping("/profile")
//    @PreAuthorize("hasAuthority('MANAGER')")
//    public ResponseEntity<Company> getCompanyProfile() {
//        AppUser currentUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return ResponseEntity.ok().body(companyRepo.findByEmail(currentUser.getEmail()));
//    }

    @GetMapping("/all")
    public ResponseEntity<List<Company>> getCompanies(){
        return new ResponseEntity<>(companyService.getCompanies(), OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company){
        return new ResponseEntity<>(companyService.updateCompany(company), OK);
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerCompany(@RequestBody RegisterCompanyDto registerDto){
        Company registeredCompany = companyService.registerCompany(registerDto);
        final String jwtToken = jwtServices.generateJwt(registerDto.getUser());

        return new ResponseEntity<>(jwtToken, CREATED);
       // return new ResponseEntity<>(registeredCompany, CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteCompanyById(@PathVariable int id){
        companyService.deleteCompany(id);
    }
}

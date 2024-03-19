package com.umerscode.Jobboard.Controller;

import com.umerscode.Jobboard.Entity.Company;
import com.umerscode.Jobboard.Entity.Job;
import com.umerscode.Jobboard.Service.JobServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {

    @Autowired
    private final JobServiceImpl jobService;

    @GetMapping("/all")
    public ResponseEntity<List<Job>> getJobs() {
        return new ResponseEntity<>(jobService.getJobs(), HttpStatus.OK);
    }

    @GetMapping(path = "/type/{jobType}")
    public ResponseEntity<List<Job>> getJobByType(@PathVariable("jobType") String jobType){
        return new ResponseEntity<>(jobService.getJobsByJobType(jobType), HttpStatus.OK);
    }


    @GetMapping("/company/{email}")
    public ResponseEntity<List<Job>> getJobsByCompanyEmail(@PathVariable String email){
        return new ResponseEntity<>(jobService.getJobsByCompanyEmail(email), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('EMPLOYER')")
    public ResponseEntity<Job> createJob(@RequestBody Job newJob) {
        return new ResponseEntity<>(jobService.creatJob(newJob), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('EMPLOYER')")
    public ResponseEntity<Job> updateJob(@RequestBody Job job) {
        return new ResponseEntity<>(jobService.updateJob(job), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYER')")
    public void deleteJobById(@PathVariable("id") int jobId) {
        jobService.deleteJobById(jobId);
    }

}

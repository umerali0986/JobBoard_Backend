package com.umerscode.Jobboard.Controller;

import com.umerscode.Jobboard.Entity.Company;
import com.umerscode.Jobboard.Entity.Job;
import com.umerscode.Jobboard.Service.JobServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<Job>> getJobByType(@RequestParam("job_type") String jobType){
        return new ResponseEntity<>(jobService.getJobsByJobType(jobType), HttpStatus.OK);
    }


    @GetMapping("/company/{id}")
    public ResponseEntity<List<Job>> getJobByCompanyId(@PathVariable long id){
        return new ResponseEntity<>(jobService.getJobsByCompanyId(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Job> createJob(@RequestBody Job newJob) {
        return new ResponseEntity<>(jobService.creatJob(newJob), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Job> updateJob(@RequestBody Job job) {
        return new ResponseEntity<>(jobService.updateJob(job), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteJobById(@PathVariable("id") int jobId) {
        jobService.deleteJobById(jobId);
    }

}

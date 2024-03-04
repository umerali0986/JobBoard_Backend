package com.umerscode.Jobboard.Service;

import com.umerscode.Jobboard.Entity.Job;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JobService {

    List<Job> getJobs();
    List<Job> getJobsByJobType(String jobType);
    List<Job> getJobsByCompanyId(long companyId);
    Job updateJob(Job job);
    Job creatJob(Job job);
    void deleteJobById(int id);
}

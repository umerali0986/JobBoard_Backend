package com.umerscode.Jobboard.Service;

import com.umerscode.Jobboard.Entity.Job;
import com.umerscode.Jobboard.Repository.JobRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobServiceImpl implements JobService{

    @Autowired
    private final JobRepo jobRepo;



    @Override
    public List<Job> getJobs() {
        return jobRepo.findAll();
    }


    @Override
    public List<Job> getJobsByJobType(String jobType) {
        return jobRepo.findByJobType(jobType).get();
    }

    @Override
    public List<Job> getJobsByCompanyId(long companyId) {
        return jobRepo.findByCompanyId((long)companyId).get();
    }

    @Override
    public Job updateJob(Job job) {
        Job existJob = jobRepo.findById(job.getId()).get();

        existJob.setEducationLevel(job.getEducationLevel());
        existJob.setJobType(job.getJobType());
        existJob.setPayPerHour(job.getPayPerHour());
        existJob.setYearsOfExperience(job.getYearsOfExperience());
       // existJob.setCompany(job.getCompany());
        return existJob;
    }

    @Override
    public Job creatJob(Job job) {
        job.setJobNumber(UUID.randomUUID().toString().substring(0,8));
        return jobRepo.save(job);
    }

    @Override
    public void deleteJobById(int id) {
    jobRepo.deleteById((long) id);
    }
}

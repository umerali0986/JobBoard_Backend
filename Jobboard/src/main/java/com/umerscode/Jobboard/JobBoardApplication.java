package com.umerscode.Jobboard;

import com.umerscode.Jobboard.Entity.*;
import com.umerscode.Jobboard.Repository.AppUserRepo;
import com.umerscode.Jobboard.Repository.CompanyRepo;
import com.umerscode.Jobboard.Repository.EmployeeRepo;
import com.umerscode.Jobboard.Repository.JobRepo;
import com.umerscode.Jobboard.Service.AppUserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.UUID;

@SpringBootApplication
public class JobBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobBoardApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(CompanyRepo companyRepo, JobRepo jobRepo,
											   EmployeeRepo employeeRepo, AppUserServiceImpl userService){

		return args->{
			Employee employee1 = new Employee(1L, UUID.randomUUID().toString().substring(0,8),"Mikel","Albert",
								"mikelalbert@gmail.com","8923749201","Front-end Developer","123 ave, LA, 23853",1,"Bachelor degree");

			Employee employee2 = new Employee(2L, UUID.randomUUID().toString().substring(0,8),"Alex","Luke",
					"alexluke@gmail.com","72746913801","Back-end Developer","234 ave, CA, 12354",3,"Master degree");

			employeeRepo.saveAll(Arrays.asList(employee1,employee2));

			Company company1 = new Company(1L,"Microsoft", "microsoft@gmail.com","2862793692","984 Ave, NY, 28473");
			Company company2 = new Company(2L,"Amazon", "amazon@gmail.com","2847208473"," 14 90th Ne, NY, 28424");

			companyRepo.saveAll(Arrays.asList(company1,company2));

			Job job1 = new Job(1l, UUID.randomUUID().toString().substring(0,8),"Front-end Developer",45.00,"Bachelor Degree",2,company1);
			Job job2 = new Job(2l, UUID.randomUUID().toString().substring(0,8),"Back-end Developer",46.00,"Bachelor Degree",3,company2);
			Job job3 = new Job(3l, UUID.randomUUID().toString().substring(0,8),"Back-end Developer",38.00,"Bachelor Degree",0,company2);

			jobRepo.saveAll(Arrays.asList(job1,job2,job3));


			AppUser user1 = new AppUser(1L,"mikelalbert@gmail.com","1234", Role.EMPLOYEE);
			AppUser user2 = new AppUser(2L,"alexluke@gmail.com","1234", Role.EMPLOYEE);

			AppUser user3 = new AppUser(3L,"microsoft@gmail.com","1234", Role.EMPLOYER);
			AppUser user4 = new AppUser(4L,"amazon@gmail.com","1234", Role.EMPLOYER);

			userService.createUser(user1);
			userService.createUser(user2);
			userService.createUser(user3);
			userService.createUser(user4);

		};
	}

}

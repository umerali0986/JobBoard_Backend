package com.umerscode.Jobboard.Dto;

import com.umerscode.Jobboard.Entity.AppUser;
import com.umerscode.Jobboard.Entity.Employee;
//import com.umerscode.jobboard.Entity.AppUser;
//import com.umerscode.jobboard.Entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterEmployeeDto {

    //@NotEmpty(message = "Invalid information") @NotNull(message = "Invalid information")
    private AppUser user;
    //@NotEmpty(message = "Invalid information") @NotNull(message = "Invalid information")
    private Employee employee;

}

package com.umerscode.Jobboard.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String employeeCode;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phone;
    private String jobType;
    private String address;
    private int yearsOfExperience;
    private String educationLevel;
}

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
public class Job implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String jobNumber;
    private String jobType;
    private double payPerHour;
    private String educationLevel;
    private int yearsOfExperience;

    @JoinColumn(name = "company_id")
    @OneToOne
    private Company company;
}

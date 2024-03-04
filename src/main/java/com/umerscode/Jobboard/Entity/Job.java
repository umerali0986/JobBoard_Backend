package com.umerscode.Jobboard.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Job implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(unique = true) @Getter
    private String jobNumber;
    @Getter
    private String jobType;
    @Getter
    private double payPerHour;
    @Getter
    private String educationLevel;
    @Getter
    private int yearsOfExperience;

    @JoinColumn(name = "company_id")
    @OneToOne
    private Company company;
}

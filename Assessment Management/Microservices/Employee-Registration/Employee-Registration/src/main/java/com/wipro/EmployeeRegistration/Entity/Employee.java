package com.wipro.EmployeeRegistration.Entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "empid")
    private int empId;
    @Column(name = "atype")
    private String assessmentType;
    @Column(name = "aname")
    private String assessmentName;
    @Column(name = "adate")
    private String assessmentDate;
}

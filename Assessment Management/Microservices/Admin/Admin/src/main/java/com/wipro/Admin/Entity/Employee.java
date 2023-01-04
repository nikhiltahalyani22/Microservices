package com.wipro.Admin.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {


    private int empId;
    private String assessmentType;
    private String assessmentName;
    private String assessmentDate;
}

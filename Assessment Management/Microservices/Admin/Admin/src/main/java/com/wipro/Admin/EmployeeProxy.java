package com.wipro.Admin;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.wipro.Admin.Entity.Employee;


@FeignClient(value = "EMPLOYEE-REGISTARTION")
public interface EmployeeProxy {

    @GetMapping("/employee/all")
    public List<Employee> findAll();

}

package com.wipro.EmployeeRegistration.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.EmployeeRegistration.Entity.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}

package com.wipro.EmployeeRegistration.Services;

import java.util.List;

import com.wipro.EmployeeRegistration.Entity.Employee;
import org.springframework.web.servlet.ModelAndView;


public interface EmployeeService {

    public List<Employee> findAllEmployee();

    public ModelAndView addTechnicalAssessment(Employee theEmployee);

    public ModelAndView showTechnicalAssessmentForm(int empId);

    public ModelAndView showBehavioralAssessmentForm(int empId);

    public ModelAndView addBehavioralAssessment(Employee theEmployee);


}

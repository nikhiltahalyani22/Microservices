package com.wipro.EmployeeRegistration.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wipro.EmployeeRegistration.AdminProxy;
import com.wipro.EmployeeRegistration.Entity.Assessment;
import com.wipro.EmployeeRegistration.Entity.Employee;
import com.wipro.EmployeeRegistration.Services.EmployeeService;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/employee")
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/assessmentType")
    public String assessmentType(Model theModel) {
        return "Assessment-Type";
    }

    @GetMapping("/showTechnicalForm/*")
    public ModelAndView assessmentTechnical(@RequestParam("empid") int empId) {
        return employeeService.showTechnicalAssessmentForm(empId);
    }

    @GetMapping("/showBehavioralForm/*")
    public ModelAndView assessmentBehavioral(@RequestParam("empid") int empId) {
        return employeeService.showBehavioralAssessmentForm(empId);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Employee> findAll() {
        return employeeService.findAllEmployee();
    }

    @PostMapping("/technicalAssessmentScheduled")
    public ModelAndView technicalAssessmentSave(@ModelAttribute("assessment") Employee theAssessment) {
        return employeeService.addTechnicalAssessment(theAssessment);
    }

    @PostMapping("/BehavioralAssessmentScheduled")
    public ModelAndView behavioralAssessmentSave(@ModelAttribute("assessment") Employee theAssessment) {
        return employeeService.addBehavioralAssessment(theAssessment);
    }


}

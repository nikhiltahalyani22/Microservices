package com.wipro.EmployeeRegistration.Services;

import java.util.List;

import com.wipro.EmployeeRegistration.AdminProxy;
import com.wipro.EmployeeRegistration.Entity.Assessment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

import com.wipro.EmployeeRegistration.Entity.Employee;
import com.wipro.EmployeeRegistration.Repository.EmployeeRepository;
import org.springframework.web.servlet.ModelAndView;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final AdminProxy adminProxy;

    private final CircuitBreakerFactory circuitBreakerFactory;

    private final LoadBalancerClient loadBalancerClient;

    @Value("${pivotal.login.name}")
    private String lurl;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, AdminProxy adminProxy, CircuitBreakerFactory circuitBreakerFactory, LoadBalancerClient loadBalancerClient) {
        this.employeeRepository = employeeRepository;
        this.adminProxy = adminProxy;
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.loadBalancerClient = loadBalancerClient;
    }

    @Override
    public List<Employee> findAllEmployee() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("Emp");
        return circuitBreaker.run(() -> employeeRepository.findAll());
    }

    @Override
    public ModelAndView showTechnicalAssessmentForm(int empId) {
        ModelAndView mv = new ModelAndView();
        Employee theEmployee = new Employee();
        List<Assessment> theAssessment = adminProxy.ShowAllTechAssessment();
        mv.addObject("assessmentType", theAssessment);
        mv.addObject("assessment", theEmployee);
        mv.addObject("loginEmpId", empId);
        mv.addObject("date",java.time.LocalDate.now());
        mv.addObject("lurl", (loadBalancerClient.choose(lurl).getUri() + "" + "/login"));
        mv.setViewName("TechnicalForm");
        return mv;
    }

    @Override
    public ModelAndView showBehavioralAssessmentForm(int empId) {
        ModelAndView mv = new ModelAndView();
        Employee theEmployee = new Employee();
        List<Assessment> theAssessment = adminProxy.ShowAllBehavioralAssessment();
        mv.addObject("assessmentType", theAssessment);
        mv.addObject("assessment", theEmployee);
        mv.addObject("loginEmpId", empId);
        mv.addObject("date",java.time.LocalDate.now());
        mv.addObject("lurl", (loadBalancerClient.choose(lurl).getUri() + "" + "/login"));
        mv.setViewName("BehavioralForm");
        return mv;
    }

    @Override
    public ModelAndView addTechnicalAssessment(Employee theEmployee) {
        ModelAndView mv = new ModelAndView();
        theEmployee.setId(0);
        theEmployee.setAssessmentType("Technical");
        employeeRepository.save(theEmployee);
        mv.addObject("employee",theEmployee);
        mv.addObject("lurl", (loadBalancerClient.choose(lurl).getUri() + "" + "/login"));
        mv.setViewName("ConfirmationPage");
        return mv;
    }

    @Override
    public ModelAndView addBehavioralAssessment(Employee theEmployee) {
        ModelAndView mv = new ModelAndView();
        theEmployee.setId(0);
        theEmployee.setAssessmentType("Behavioral");
        employeeRepository.save(theEmployee);
        mv.addObject("employee",theEmployee);
        mv.addObject("lurl", (loadBalancerClient.choose(lurl).getUri() + "" + "/login"));
        mv.setViewName("ConfirmationPage");
        return mv;
    }



}

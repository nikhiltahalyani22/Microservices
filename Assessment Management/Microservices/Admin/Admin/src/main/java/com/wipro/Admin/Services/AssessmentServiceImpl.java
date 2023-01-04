package com.wipro.Admin.Services;

import java.util.List;

import com.wipro.Admin.EmployeeProxy;
import com.wipro.Admin.Entity.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

import com.wipro.Admin.Entity.Assessment;
import com.wipro.Admin.Repository.AssessmentRepository;
import org.springframework.web.servlet.ModelAndView;


@Service
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository assessmentRepository;
    private final CircuitBreakerFactory circuitBreakerFactory;
    private final LoadBalancerClient loadBalancerClient;
    private final EmployeeProxy employeeProxy;

    @Value("${pivotal.login.name}")
    private String lurl;

    public AssessmentServiceImpl(AssessmentRepository assessmentRepository, CircuitBreakerFactory circuitBreakerFactory, LoadBalancerClient loadBalancerClient, EmployeeProxy employeeProxy) {
        this.assessmentRepository = assessmentRepository;
        this.circuitBreakerFactory = circuitBreakerFactory;
        this.loadBalancerClient = loadBalancerClient;
        this.employeeProxy = employeeProxy;
    }


    @Override
    public List<Assessment> findAllAssessment() {
        return assessmentRepository.findAll();
    }

    @Override
    public Assessment addAssessment(Assessment theAssessment) {
        assessmentRepository.save(theAssessment);
        return theAssessment;
    }

    @Override
    public List<Assessment> findAssessmentByType(String TheAssessmentType) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("Assessment");
        return circuitBreaker.run(() -> assessmentRepository.findByAssessmentType(TheAssessmentType));
    }

    @Override
    public ModelAndView showAssessmentForm() {
        ModelAndView mv = new ModelAndView();
        Assessment theAssessment = new Assessment();
        mv.addObject("assessment", theAssessment);
        mv.addObject("allAssessment", assessmentRepository.findAll());
        mv.addObject("lurl", (loadBalancerClient.choose(lurl).getUri() + "" + "/login"));
        mv.setViewName("AssessmentForm");
        return mv;
    }

    @Override
    public ModelAndView showAllScheduledAssessment(){
        ModelAndView mv = new ModelAndView();
        List<Employee> theEmployee = employeeProxy.findAll();
        mv.addObject("employee", theEmployee);
        mv.addObject("lurl", (loadBalancerClient.choose(lurl).getUri() + "" + "/login"));
        mv.setViewName("ScheduledAssessment");
        return mv;
    }

}

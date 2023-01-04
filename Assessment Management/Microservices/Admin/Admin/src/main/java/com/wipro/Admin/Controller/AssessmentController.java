package com.wipro.Admin.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wipro.Admin.EmployeeProxy;
import com.wipro.Admin.Entity.Employee;
import com.wipro.Admin.Entity.Assessment;
import com.wipro.Admin.Services.AssessmentService;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AssessmentController {

    private AssessmentService assessmentService;

    @Autowired
    public AssessmentController(AssessmentService theAssessmentService) {
        assessmentService = theAssessmentService;
    }

    @GetMapping("/showAssessmentform")
    public ModelAndView assessmentTechnical(Model theModel) {
        return (assessmentService.showAssessmentForm());
    }

    @PostMapping("/AssessmentAdd")
    public String AssessmentSave(@ModelAttribute("assessment") Assessment theAssessment) {
        assessmentService.addAssessment(theAssessment);
        return "redirect:/admin/showAssessmentform";

    }

    @GetMapping("/showAllScheduledAssessment")
    public ModelAndView ShowAllAssessment(Model theModel) {
        return (assessmentService.showAllScheduledAssessment());
    }

    @GetMapping("/showAllTechnicalAssessment")
    @ResponseBody
    public List<Assessment> ShowAllTechAssessment() {
        return (assessmentService.findAssessmentByType("Technical"));

    }

    @GetMapping("/showAllBehavioralAssessment")
    @ResponseBody
    public List<Assessment> ShowAllBehavioralAssessment() {
        return (assessmentService.findAssessmentByType("Behavioral"));

    }


}

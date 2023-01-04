package com.wipro.Admin.Services;

import java.util.List;

import com.wipro.Admin.Entity.Assessment;
import org.springframework.web.servlet.ModelAndView;


public interface AssessmentService {

    public List<Assessment> findAllAssessment();

    public Assessment addAssessment(Assessment theAssessment);

    public List<Assessment> findAssessmentByType(String theAssessmentType);

    public ModelAndView showAssessmentForm();

    public ModelAndView showAllScheduledAssessment();


}

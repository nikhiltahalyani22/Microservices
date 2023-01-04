package com.wipro.Login;

import java.util.List;

import com.wipro.Login.Enitiy.Assessment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;




@FeignClient(value = "Admin-Application")
public interface AdminProxy {

    @GetMapping("/admin/showAllTechnicalAssessment")
    public List<Assessment> ShowAllTechAssessment();

    @GetMapping("/admin/showAllBehavioralAssessment")
    public List<Assessment> ShowAllBehavioralAssessment();

}

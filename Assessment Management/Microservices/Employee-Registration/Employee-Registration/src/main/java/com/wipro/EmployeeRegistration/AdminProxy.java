package com.wipro.EmployeeRegistration;

        import java.util.List;

        import org.springframework.cloud.openfeign.FeignClient;
        import org.springframework.web.bind.annotation.GetMapping;

        import com.wipro.EmployeeRegistration.Entity.Assessment;


@FeignClient(value = "Admin-Application")
public interface AdminProxy {

    @GetMapping("/admin/showAllTechnicalAssessment")
    public List<Assessment> ShowAllTechAssessment();

    @GetMapping("/admin/showAllBehavioralAssessment")
    public List<Assessment> ShowAllBehavioralAssessment();

}

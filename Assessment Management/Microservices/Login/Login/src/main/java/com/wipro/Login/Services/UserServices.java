package com.wipro.Login.Services;


import com.wipro.Login.AdminProxy;
import com.wipro.Login.Enitiy.Assessment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

import com.wipro.Login.Enitiy.User;
import com.wipro.Login.Repository.UserRepository;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Service
public class UserServices implements UserServiceInterface {

    private final UserRepository userRepository;

    private final AdminProxy adminProxy;

    private final LoadBalancerClient loadBalancerClient;

    @Value("${pivotal.employee.name}")
    private String Eurl;

    @Value("${pivotal.login.name}")
    private String lurl;

    @Value("${pivotal.admin.name}")
    private String aurl;

    public UserServices(UserRepository userRepository, AdminProxy adminProxy, LoadBalancerClient loadBalancerClient) {
        this.userRepository = userRepository;
        this.adminProxy = adminProxy;
        this.loadBalancerClient = loadBalancerClient;
    }


    @Override
    public ModelAndView loginCheck(User theUser) {
        User user = userRepository.getById(theUser.getEmpid());
        ModelAndView mv = new ModelAndView();
        if (user.getEmpid() != theUser.getEmpid()) {
            mv.setViewName("UserLoginForm");
            return mv;
        } else if (user.getPassword().equals(theUser.getPassword())) {
            if (theUser.getEmpid() == 101) {
                mv.addObject("aurl", (loadBalancerClient.choose(aurl).getUri() + "" + "/admin/showAssessmentform"));
                mv.addObject("asurl", (loadBalancerClient.choose(aurl).getUri() + "" + "/admin/showAllScheduledAssessment"));
                mv.addObject("lurl", (loadBalancerClient.choose(lurl).getUri() + "" + "/login"));
                mv.setViewName("Admin");
                return mv;
            } else {
                mv.addObject("empid", theUser.getEmpid());
                List<Assessment> techAssessment = adminProxy.ShowAllTechAssessment();
                List<Assessment> behavAssessment = adminProxy.ShowAllBehavioralAssessment();
                mv.addObject("techAssessment",techAssessment);
                mv.addObject("behavAssessment",behavAssessment);
                mv.addObject("Eurl", (loadBalancerClient.choose(Eurl).getUri() + "" + "/employee/showTechnicalForm/"));
                mv.addObject("Burl", (loadBalancerClient.choose(Eurl).getUri() + "" + "/employee/showBehavioralForm/"));
                mv.addObject("lurl", (loadBalancerClient.choose(lurl).getUri() + "" + "/login"));
                mv.setViewName("Assessment-Type");
                return mv;
            }
        } else {
            String error="error";
            mv.addObject("Pass",error);
            mv.setViewName("UserLoginForm");
            return mv;
        }
    }

}

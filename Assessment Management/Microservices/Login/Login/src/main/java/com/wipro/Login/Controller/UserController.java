package com.wipro.Login.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wipro.Login.Enitiy.User;
import com.wipro.Login.Services.UserServices;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/")
public class UserController {


    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/login")
    public String userLoginForm(Model theModel) {
        theModel.addAttribute("user", new User());
        return "UserLoginForm";
    }

    @PostMapping("/success")
    public ModelAndView userLoginCheck(@ModelAttribute("user") User theUser, Model theModel)  {
           return userServices.loginCheck(theUser);

    }
}

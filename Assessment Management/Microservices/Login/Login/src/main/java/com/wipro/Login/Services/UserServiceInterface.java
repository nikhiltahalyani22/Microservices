package com.wipro.Login.Services;

import com.wipro.Login.Enitiy.User;
import org.springframework.web.servlet.ModelAndView;

public interface UserServiceInterface {

    public ModelAndView loginCheck(User theUser);

}

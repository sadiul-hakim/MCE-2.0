package org.learn.cms.controller;

import org.learn.cms.user.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
    @GetMapping("/")
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard(ModelAndView modelAndView) {
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView) {

        UserDTO dto = new UserDTO();
        modelAndView.setViewName("register");
        modelAndView.addObject("userDTO", dto);

        return modelAndView;
    }
}

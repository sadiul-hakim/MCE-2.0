package org.learn.cms.controller;

import jakarta.validation.Valid;
import org.learn.cms.pojo.ContactUs;
import org.learn.cms.user.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
    @GetMapping("/")
    public ModelAndView home(ModelAndView modelAndView) {

        modelAndView.addObject("contact_us", new ContactUs());
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

    @PostMapping("/contact_us")
    public ModelAndView contactUs(@Valid @ModelAttribute ContactUs contactUs, BindingResult bindingResult,
                                  ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("index");
            modelAndView.addObject("mail_sent", false);
            return modelAndView;
        }

        modelAndView.setViewName("index");
        modelAndView.addObject("mail_sent", true);
        modelAndView.addObject("contact_us", new ContactUs());
        return modelAndView;
    }
}

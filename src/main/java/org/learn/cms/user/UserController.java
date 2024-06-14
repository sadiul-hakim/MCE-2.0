package org.learn.cms.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
class UserController {
    private final UserService userService;

    @PostMapping(value = "/")
    ModelAndView saveUser(@Valid @ModelAttribute UserDTO user, BindingResult bindingResult, ModelAndView modelAndView) {

        // If user filled valid value redirect to register page
        if (bindingResult.hasFieldErrors()) {
            modelAndView.setViewName("register");
            return modelAndView;
        }

        // Otherwise save the user
        userService.save(user);
        modelAndView.addObject("userDTO", new UserDTO());
        modelAndView.setViewName("redirect:/register");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getOneUser(@PathVariable long id, ModelAndView modelAndView) {
        UserDTO user = userService.getById(id);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}

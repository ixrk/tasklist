package pl.sda.tasklist.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.tasklist.dto.SignUpForm;
import pl.sda.tasklist.exception.UserExistsException;
import pl.sda.tasklist.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/sign-up")
public class SingUpController {

    private final UserServiceImpl userService;
    private final Validator validator;

    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    public SingUpController(UserServiceImpl userService, @Qualifier("signUpValidator")Validator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @GetMapping
    public ModelAndView getSignUpPage() {
        ModelAndView modelAndView = new ModelAndView("signUp");
        modelAndView.addObject("signUpForm", new SignUpForm());
        return modelAndView;
    }

    @PostMapping
    public String createUser(@ModelAttribute @Validated SignUpForm form, BindingResult bindingResult) throws UserExistsException {
        if (bindingResult.hasErrors()) {
            return "signUp";
        }

        userService.saveUser(form);

        return "redirect:/sign-in";
    }
}

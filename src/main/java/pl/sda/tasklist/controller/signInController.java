package pl.sda.tasklist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.tasklist.dto.SignInForm;

@Controller
@RequestMapping("/sign-in")
public class signInController {

    @GetMapping
    ModelAndView getSignInPage(){
        ModelAndView modelAndView = new ModelAndView("signIn");
        modelAndView.addObject("signInForm",new SignInForm());
        return modelAndView;
    }

}

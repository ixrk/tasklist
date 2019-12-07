package pl.sda.tasklist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.tasklist.dao.UserRepository;

@Controller
public class AdminController {
    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    ModelAndView getAllUsers(){
        ModelAndView mnv = new ModelAndView("user-list");
        mnv.addObject("userList",userRepository.findAll());
        return mnv;
    }
}
